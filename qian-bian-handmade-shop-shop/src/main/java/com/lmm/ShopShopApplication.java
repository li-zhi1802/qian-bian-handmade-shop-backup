package com.lmm;

import com.lmm.client.UserClient;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableSwagger2Doc
@SpringBootApplication
@EnableFeignClients(clients = {UserClient.class})
public class ShopShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopShopApplication.class, args);
    }

}
