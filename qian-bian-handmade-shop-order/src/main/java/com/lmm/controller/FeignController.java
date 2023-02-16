package com.lmm.controller;

import com.lmm.service.MerchandiseOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 芝麻
 * @date : 2023-02-11 20:38
 **/
@Api(tags = "远程调用的接口")
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private MerchandiseOrderService merchandiseOrderService;

    @PutMapping("/{orderId}/{nextState}")
    public Boolean updateOrderState(@PathVariable("nextState") String nextState, @PathVariable("orderId") String orderId) {
        return merchandiseOrderService.updateOrderState(orderId, nextState);
    }
}
