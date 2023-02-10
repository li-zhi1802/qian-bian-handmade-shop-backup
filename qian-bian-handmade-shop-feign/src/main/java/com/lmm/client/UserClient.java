package com.lmm.client;

import com.lmm.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("qian-bian-user")
@RequestMapping("/user/feign")
public interface UserClient {
    /**
     * 得到用户信息
     *
     * @param phone
     * @return
     */
    @GetMapping("/phone/{phone}")
    UserInfo findUserByPhone(@PathVariable("phone") String phone);

    /**
     * 得到用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    UserInfo findUserById(@PathVariable("id") Long id);

    /**
     * 更新用户有店铺了
     *
     * @param id
     * @param shopId
     * @return
     */
    @PutMapping("/{id}/{shopId}")
    Boolean updateHasShop(@PathVariable("id") Long id, @PathVariable("shopId") Long shopId);
}
