package com.lmm.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.lmm.constant.MerchandiseOrderState;
import com.lmm.constant.RedisConstant;
import com.lmm.dto.GenerateOrder;
import com.lmm.entity.MerchandiseOrder;
import com.lmm.exception.QianBianException;
import com.lmm.properties.AlipayProperties;
import com.lmm.service.AlipayService;
import com.lmm.service.MerchandiseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author : 芝麻
 * @date : 2023-02-12 09:38
 **/
@Service
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private MerchandiseOrderService merchandiseOrderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getPayBody(String generateOrderId, Long userId) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayProperties.getGatewayUrl(),
                alipayProperties.getAppId(),
                alipayProperties.getAppPrivateKey(),
                alipayProperties.getFormat(),
                alipayProperties.getCharset(),
                alipayProperties.getAlipayPublicKey(),
                alipayProperties.getSignType());
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        String cacheKey = RedisConstant.GENERATE_ORDER_PREFIX + userId;
        GenerateOrder generateOrder = JSONUtil.toBean((String) stringRedisTemplate.opsForHash().get(cacheKey, generateOrderId), GenerateOrder.class);
        // 更新订单状态为支付中
        generateOrder.getOrderIds().forEach(o -> merchandiseOrderService.lambdaUpdate().eq(MerchandiseOrder::getId, o).set(MerchandiseOrder::getState, MerchandiseOrderState.PAYING).update());
        // 设置notifyUrl
        alipayRequest.setNotifyUrl(alipayProperties.getNotifyUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", generateOrderId);
        bizContent.put("total_amount", generateOrder.getTotalAmount());
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        alipayRequest.setBizContent(bizContent.toString());
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    @Override
    public Boolean refund(String orderId) throws AlipayApiException {
        MerchandiseOrder one = merchandiseOrderService.lambdaQuery().eq(MerchandiseOrder::getId, orderId).select(MerchandiseOrder::getActualTotal).one();
        if (one == null) {
            throw new QianBianException("没有这个订单");
        }
        BigDecimal refundAmount = one.getActualTotal();
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayProperties.getGatewayUrl(),
                alipayProperties.getAppId(),
                alipayProperties.getAppPrivateKey(),
                alipayProperties.getFormat(),
                alipayProperties.getCharset(),
                alipayProperties.getAlipayPublicKey(),
                alipayProperties.getSignType());
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("trade_no", orderId);
        bizContent.put("refund_amount", refundAmount);
        bizContent.put("out_request_no", "HZ01RF001");
        request.setBizContent(bizContent.toString());
        if (!alipayClient.execute(request).isSuccess()) {
            throw new QianBianException("退款失败，请重试");
        }
        return merchandiseOrderService.lambdaUpdate().eq(MerchandiseOrder::getId, orderId).set(MerchandiseOrder::getState, MerchandiseOrderState.REFUND_SUCCESSFUL).update();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payNotify(String generateOrderId, Long userId) {
        String cacheKey = RedisConstant.GENERATE_ORDER_PREFIX + userId;
        GenerateOrder generateOrder = JSONUtil.toBean((String) stringRedisTemplate.opsForHash().get(cacheKey, generateOrderId), GenerateOrder.class);
        // 删除大订单
        stringRedisTemplate.opsForHash().delete(cacheKey, generateOrder);
        // 修改状态
        generateOrder.getOrderIds().forEach(o -> {
            // 支付成功===待发货
            merchandiseOrderService.updateOrderState(o, MerchandiseOrderState.TO_BE_RECEIVED.getCode());
        });
    }
}
