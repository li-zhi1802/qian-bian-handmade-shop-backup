package com.lmm.service;

import com.alipay.api.AlipayApiException;

/**
 * @author : 芝麻
 * @date : 2023-02-12 09:38
 **/
public interface AlipayService {
    String getPayBody(String generateOrderId, Long userId) throws AlipayApiException;

    Boolean refund(String orderId) throws AlipayApiException;

    void payNotify(String generateOrderId, Long userId);
}
