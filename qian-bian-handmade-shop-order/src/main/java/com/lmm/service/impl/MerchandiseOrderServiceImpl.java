package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.MerchandiseClient;
import com.lmm.client.ShopClient;
import com.lmm.client.VoucherClient;
import com.lmm.constant.MerchandiseOrderState;
import com.lmm.constant.RedisConstant;
import com.lmm.dto.GenerateOrder;
import com.lmm.dto.MerchandiseItemDTO;
import com.lmm.dto.MerchandiseOrderDTO;
import com.lmm.dto.PageResult;
import com.lmm.entity.MerchandiseOrder;
import com.lmm.mapper.MerchandiseOrderMapper;
import com.lmm.service.MerchandiseOrderService;
import com.lmm.vo.MerchandiseOrderVO;
import com.lmm.vo.MerchandiseVO;
import com.lmm.vo.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.lmm.constant.SystemConstant.ORDER_PAGE_SIZE;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class MerchandiseOrderServiceImpl extends ServiceImpl<MerchandiseOrderMapper, MerchandiseOrder> implements MerchandiseOrderService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ShopClient shopClient;

    @Autowired
    private VoucherClient voucherClient;

    @Autowired
    private MerchandiseClient merchandiseClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String generateOrders(List<MerchandiseOrderDTO> ordersToBeGenerated, Long userId) {
        List<MerchandiseOrder> merchandiseOrders = ordersToBeGenerated.stream().map(go -> {
            MerchandiseOrder merchandiseOrder = BeanUtil.copyProperties(go, MerchandiseOrder.class);
            merchandiseOrder.setUserId(userId);
            if (go.getVoucherId() != null) {
                // 修改优惠券的使用数目
                voucherClient.increaseUsedAmount(go.getVoucherId());
            }
            merchandiseOrder.setMerchandises(JSONUtil.toJsonStr(go.getMerchandises()));
            merchandiseOrder.setState(MerchandiseOrderState.TO_BE_PAID.getCode());
            merchandiseOrder.setCreatedTime(LocalDateTime.now());
            return merchandiseOrder;
        }).collect(Collectors.toList());
        saveBatch(merchandiseOrders, merchandiseOrders.size());
        String generateOrderId = DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss") + RandomUtil.randomString(14);
        String cacheKey = RedisConstant.GENERATE_ORDER_PREFIX + userId;
        List<String> orderIds = merchandiseOrders.stream().map(MerchandiseOrder::getId).collect(Collectors.toList());
        BigDecimal totalAmount = new BigDecimal("0");
        for (MerchandiseOrder merchandiseOrder : merchandiseOrders) {
            totalAmount = totalAmount.add(merchandiseOrder.getActualTotal());
        }
        stringRedisTemplate.opsForHash().put(cacheKey, generateOrderId, JSONUtil.toJsonStr(new GenerateOrder(orderIds, totalAmount)));
        return generateOrderId;
    }

    @Override
    public PageResult<MerchandiseOrderVO> listOrderByPage(Long pageNum, String orderState, Long userId) {
        Page<MerchandiseOrder> pageQuery = page(
                new Page<>(pageNum, ORDER_PAGE_SIZE),
                new LambdaQueryWrapper<MerchandiseOrder>()
                        .eq(StrUtil.isNotBlank(orderState), MerchandiseOrder::getState, orderState)
                        .eq(MerchandiseOrder::getUserId, userId)
        );
        List<MerchandiseOrderVO> merchandiseOrderVOs = pageQuery.getRecords().stream().map(p -> {
            MerchandiseOrderVO merchandiseOrderVO = BeanUtil.copyProperties(p, MerchandiseOrderVO.class, "merchandises");
            merchandiseOrderVO.setShop(BeanUtil.copyProperties(shopClient.getShopById(p.getShopId()), ShopVO.class));
            merchandiseOrderVO.setMerchandises(
                    JSONUtil.toList(p.getMerchandises(), MerchandiseItemDTO.class).stream().map(item -> {
                        MerchandiseVO merchandiseVO = BeanUtil.copyProperties(merchandiseClient.getMerchandiseById(item.getId()), MerchandiseVO.class);
                        merchandiseVO.setQuantity(item.getQuantity());
                        return merchandiseVO;
                    }).collect(Collectors.toList())
            );
            return merchandiseOrderVO;
        }).collect(Collectors.toList());
        return PageResult.success(
                merchandiseOrderVOs,
                pageNum,
                pageQuery.getTotal()
        );
    }

    @Override
    public Boolean updateOrderState(String orderId, String nextState) {
        LambdaUpdateChainWrapper<MerchandiseOrder> wrapper = lambdaUpdate()
                .eq(MerchandiseOrder::getId, orderId)
                // 直接更新状态
                .set(MerchandiseOrder::getState, nextState);
        if (MerchandiseOrderState.TO_BE_RECEIVED.getCode().equals(nextState)) {
            // 支付成功
            wrapper.set(MerchandiseOrder::getPayTime, LocalDateTime.now());
        }
        return wrapper.update();
    }
}
