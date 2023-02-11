package com.lmm.config;

import com.lmm.client.UserClient;
import com.lmm.interceptor.JudgeShopKeeperInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : 芝麻
 * @date : 2023-02-10 11:21
 **/
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UserClient userClient;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JudgeShopKeeperInterceptor(userClient))
                .addPathPatterns("/voucher/shopKeeper/**");
    }
}
