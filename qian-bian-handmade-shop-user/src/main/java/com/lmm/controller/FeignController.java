package com.lmm.controller;

import com.lmm.entity.UserInfo;
import com.lmm.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author : 芝麻
 * @date : 2023-02-07 14:25
 **/
@Api(tags = "远程调用的接口")
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/phone/{phone}")
    @Cacheable(cacheNames = "feign:user:phone", key = "args[0]")
    public UserInfo findByPhone(@PathVariable("phone") String phone) {
        return userInfoService.lambdaQuery().eq(UserInfo::getPhone, phone).one();
    }

    @Cacheable(cacheNames = "feign:user:id", key = "args[0]")
    @GetMapping("/id/{id}")
    public UserInfo findUserById(@PathVariable("id") Long id) {
        return userInfoService.getById(id);
    }

    @PutMapping("/{id}/{shopId}")
    public Boolean updateHasShop(@PathVariable("id") Long id, @PathVariable("shopId") Long shopId) {
        return userInfoService.lambdaUpdate().eq(UserInfo::getId, id).set(UserInfo::getShopId, shopId).update();
    }
}
