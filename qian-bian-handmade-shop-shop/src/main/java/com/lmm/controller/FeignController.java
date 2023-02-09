package com.lmm.controller;

import com.lmm.entity.Shop;
import com.lmm.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 芝麻
 * @date : 2023-02-09 16:11
 **/
@RestController("/feign")
public class FeignController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/detail/{id}")
    @Cacheable(cacheNames = "feign:shop:", key = "args[0]")
    public Shop getShopById(@PathVariable("id") Long id) {
        return shopService.getById(id);
    }

}
