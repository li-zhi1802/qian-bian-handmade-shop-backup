package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.MerchandiseClient;
import com.lmm.client.ShopClient;
import com.lmm.dto.PageResult;
import com.lmm.entity.Shop;
import com.lmm.entity.StarShop;
import com.lmm.exception.QianBianException;
import com.lmm.mapper.StarShopMapper;
import com.lmm.service.StarShopService;
import com.lmm.vo.StarMerchandiseVO;
import com.lmm.vo.StarShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lmm.constant.RedisConstant.STAR_MERCHANDISE_KEY;
import static com.lmm.constant.RedisConstant.STAR_SHOP_KEY;
import static com.lmm.constant.SystemConstant.PAGE_SIZE;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class StarShopServiceImpl extends ServiceImpl<StarShopMapper, StarShop> implements StarShopService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ShopClient shopClient;
    @Autowired
    private MerchandiseClient merchandiseClient;

    @Override
    public Boolean addOrRemoveShop(Long shopId, Long userId) {
        String cacheKey = STAR_MERCHANDISE_KEY + userId;
        // 如果本来就已经收藏了，那就是删除
        if (lambdaQuery().eq(StarShop::getUserId, userId).eq(StarShop::getShopId, shopId).count() > 0) {
            // 从zset中删除此商品id
            stringRedisTemplate.opsForZSet().remove(cacheKey, shopId);
            return lambdaUpdate().eq(StarShop::getUserId, userId).eq(StarShop::getShopId, shopId).remove();
        } else {
            // key是star:shopId:userId
            // value是SortedSet，shopId,score为当前时间戳
            stringRedisTemplate.opsForZSet().add(cacheKey, shopId.toString(), LocalDateTime.now().getNano());
            return save(new StarShop(
                    userId,
                    shopId,
                    LocalDateTime.now()
            ));
        }
    }

    @Override
    public PageResult<StarShopVO> listStarShopByPage(Long pageNum, Long userId) {
        String cacheKey = STAR_SHOP_KEY + userId;
        long start = (long) PAGE_SIZE * pageNum;
        Set<ZSetOperations.TypedTuple<String>> tuples = stringRedisTemplate.opsForZSet().reverseRangeWithScores(cacheKey, start, start + PAGE_SIZE);
        // 说明缓存中没有
        if (tuples == null || tuples.isEmpty()) {
            Set<ZSetOperations.TypedTuple<String>> tts = new HashSet<>();
            lambdaQuery().eq(StarShop::getUserId, userId).list().forEach(s -> {
                tts.add(new DefaultTypedTuple<>(s.getShopId().toString(), (double) s.getStarTime().getNano()));
            });
            // 将数据库中所有收藏的商品都加入redis
            stringRedisTemplate.opsForZSet().add(cacheKey, tts);
            // 重新查
            tuples = stringRedisTemplate.opsForZSet().reverseRangeWithScores(cacheKey, start, start + PAGE_SIZE);
        }
        if (tuples == null || tuples.isEmpty()) {
            throw new QianBianException("商铺收藏的缓存出错");
        }
        List<StarShopVO> shopVOs = new LinkedList<>();
        for (ZSetOperations.TypedTuple<String> next : tuples) {
            Long shopId = Long.parseLong(next.getValue());
            Shop shop = shopClient.getShopById(shopId);
            StarShopVO starShopVO = BeanUtil.copyProperties(shop, StarShopVO.class);
            // 随便在这家店中拿5个商品
            starShopVO.setMerchandises(
                    merchandiseClient.randomMerchandiseByShopId(shopId)
                            .stream()
                            .map(m -> BeanUtil.copyProperties(m, StarMerchandiseVO.class))
                            .collect(Collectors.toList())
            );
            shopVOs.add(starShopVO);
        }
        return PageResult.success(
                shopVOs,
                pageNum,
                stringRedisTemplate.opsForZSet().size(cacheKey)
        );
    }

    @Override
    public Boolean deleteStarShops(List<Long> shopId, Long userId) {
        String cacheKey = STAR_SHOP_KEY + userId;
        // 删除redis中的数据
        stringRedisTemplate.opsForZSet().remove(cacheKey, shopId);
        // 删除数据库中的数据
        return lambdaUpdate()
                .in(StarShop::getShopId, shopId)
                .eq(StarShop::getUserId, userId).remove();
    }
}
