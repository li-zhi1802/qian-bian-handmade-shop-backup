package com.lmm.controller;

import com.lmm.service.MerchandiseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 芝麻
 * @date : 2023-02-11 20:38
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private MerchandiseOrderService merchandiseOrderService;

    @PutMapping("/{orderId}/{nextState}")
    public Boolean updateOrderState(@PathVariable String nextState, @PathVariable String orderId) {
        return merchandiseOrderService.updateOrderState(orderId, nextState);
    }
}
