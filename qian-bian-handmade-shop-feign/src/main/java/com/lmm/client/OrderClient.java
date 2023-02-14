package com.lmm.client;

import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : 芝麻
 * @date : 2023-02-14 11:33
 **/
@Api(tags = "订单Feign客户端")
@FeignClient("qian-bian-order")
@RequestMapping("/order/feign")
public interface OrderClient {
    /**
     * 修改订单状态
     *
     * @param nextState
     * @param orderId
     * @return
     */
    @PutMapping("/{orderId}/{nextState}")
    Boolean updateOrderState(@PathVariable("nextState") String nextState, @PathVariable("orderId") String orderId);
}
