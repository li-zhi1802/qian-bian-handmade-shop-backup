package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.MerchandiseClient;
import com.lmm.dto.PageResult;
import com.lmm.entity.Merchandise;
import com.lmm.entity.StarMerchandise;
import com.lmm.exception.QianBianException;
import com.lmm.mapper.StarMerchandiseMapper;
import com.lmm.service.StarMerchandiseService;
import com.lmm.vo.StarMerchandiseVO;
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

import static com.lmm.constant.RedisConstant.STAR_MERCHANDISE_KEY;
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
public class StarMerchandiseServiceImpl extends ServiceImpl<StarMerchandiseMapper, StarMerchandise> implements StarMerchandiseService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MerchandiseClient merchandiseClient;

    @Override
    public Boolean addOrRemoveMerchandise(Long merchandiseId, Long userId) {
        String cacheKey = STAR_MERCHANDISE_KEY + userId;
        String merchandiseIdStr = merchandiseId.toString();
        // 如果本来就已经收藏了，那就是删除
        if (lambdaQuery().eq(StarMerchandise::getUserId, userId).eq(StarMerchandise::getMerchandiseId, merchandiseId).count() > 0) {
            // 从zset中删除此商品id
            stringRedisTemplate.opsForZSet().remove(cacheKey, merchandiseIdStr);
            return lambdaUpdate().eq(StarMerchandise::getUserId, userId).eq(StarMerchandise::getMerchandiseId, merchandiseId).remove();
        } else {
            // key是star:merchandise:userId
            // value是SortedSet，merchandiseId,score为当前时间戳
            stringRedisTemplate.opsForZSet().add(cacheKey, merchandiseIdStr, LocalDateTime.now().getNano());
            return save(new StarMerchandise(
                    userId,
                    merchandiseId,
                    LocalDateTime.now()
            ));
        }
    }

    @Override
    public PageResult<StarMerchandiseVO> listStarMerchandiseByPage(Long pageNum, Long userId) {
        String cacheKey = STAR_MERCHANDISE_KEY + userId;
        long start = (long) PAGE_SIZE * (pageNum - 1);
        Set<ZSetOperations.TypedTuple<String>> tuples = stringRedisTemplate.opsForZSet().reverseRangeWithScores(cacheKey, start, start + PAGE_SIZE);
        // 说明缓存中没有
        if (tuples == null || tuples.isEmpty()) {
            Set<ZSetOperations.TypedTuple<String>> tts = new HashSet<>();
            lambdaQuery().eq(StarMerchandise::getUserId, userId).list().forEach(s -> {
                tts.add(new DefaultTypedTuple<>(s.getMerchandiseId().toString(), (double) s.getStarTime().getNano()));
            });
            // 将数据库中所有收藏的商品都加入redis
            stringRedisTemplate.opsForZSet().add(cacheKey, tts);
            // 重新查
            tuples = stringRedisTemplate.opsForZSet().reverseRangeWithScores(cacheKey, start, start + PAGE_SIZE);
        }
        if (tuples == null || tuples.isEmpty()) {
            throw new QianBianException("商品收藏的缓存出错");
        }
        List<StarMerchandiseVO> merchandiseVOs = new LinkedList<>();
        for (ZSetOperations.TypedTuple<String> next : tuples) {
            Long merchandiseId = Long.parseLong(next.getValue());
            Merchandise merchandise = merchandiseClient.getMerchandiseById(merchandiseId);
            StarMerchandiseVO starMerchandiseVO = BeanUtil.copyProperties(merchandise, StarMerchandiseVO.class);
            // 取第一个图片
            starMerchandiseVO.setPicUris(JSONUtil.toList(starMerchandiseVO.getPicUris(), String.class).get(0));
            merchandiseVOs.add(starMerchandiseVO);
        }
        return PageResult.success(
                merchandiseVOs,
                pageNum,
                stringRedisTemplate.opsForZSet().size(cacheKey)
        );
    }

    @Override
    public Boolean deleteStarMerchandises(List<Long> merchandiseId, Long userId) {
        String cacheKey = STAR_MERCHANDISE_KEY + userId;
        // 删除redis中的数据
        stringRedisTemplate.opsForZSet().remove(cacheKey, merchandiseId.stream().map(Object::toString).toArray());
        // 删除数据库中的数据
        return lambdaUpdate()
                .in(StarMerchandise::getMerchandiseId, merchandiseId)
                .eq(StarMerchandise::getUserId, userId).remove();
    }
}
