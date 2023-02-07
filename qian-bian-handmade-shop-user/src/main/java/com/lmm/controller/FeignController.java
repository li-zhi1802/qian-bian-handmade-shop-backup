package com.lmm.controller;

import com.lmm.entity.UserInfo;
import com.lmm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 芝麻
 * @date : 2023-02-07 14:25
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/detail/{phone}")
    public UserInfo findByPhone(@PathVariable("phone") String phone) {
        return userInfoService.lambdaQuery().eq(UserInfo::getPhone, phone).one();
    }
}
