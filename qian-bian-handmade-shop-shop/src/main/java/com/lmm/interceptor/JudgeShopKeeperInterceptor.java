package com.lmm.interceptor;

import com.lmm.client.UserClient;
import com.lmm.entity.UserInfo;
import com.lmm.exception.QianBianException;
import com.lmm.util.SecurityUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JudgeShopKeeperInterceptor implements HandlerInterceptor {
    private UserClient userClient;

    public JudgeShopKeeperInterceptor(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是不是商家
        UserInfo userInfo = userClient.findUserById(SecurityUtil.getUser().getId());
        Long shopId = userInfo.getShopId();
        if (shopId == null) {
            throw new QianBianException("您不是商家，请不要访问商家接口");
        }
        return true;
    }
}
