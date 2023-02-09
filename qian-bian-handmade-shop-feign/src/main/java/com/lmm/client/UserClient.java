package com.lmm.client;

import com.lmm.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/detail/{phone}")
    UserInfo findUserByPhone(@PathVariable("phone") String phone);
}
