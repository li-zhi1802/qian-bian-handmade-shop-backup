package com.lmm.client;

import com.lmm.entity.ShippingAddress;
import com.lmm.entity.Shop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("qian-bian-shop")
@RequestMapping("/shop/feign")
public interface ShopClient {
    /**
     * 得到店铺信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Shop getShopById(@PathVariable("id") Long id);

    @GetMapping("/shipping-address/default/{shopId}")
    ShippingAddress defaultShippingAddress(@PathVariable("shopId") Long shopId);

    @PutMapping
    Boolean updateAvgScore(Shop shop);
}
