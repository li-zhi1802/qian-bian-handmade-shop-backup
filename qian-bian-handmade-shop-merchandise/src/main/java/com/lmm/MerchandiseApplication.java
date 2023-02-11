package com.lmm;

import com.lmm.client.ShopClient;
import com.lmm.client.SystemClient;
import com.lmm.client.UserClient;
import com.lmm.client.VoucherClient;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableSwagger2Doc
@SpringBootApplication
@EnableFeignClients(clients = {ShopClient.class, SystemClient.class, VoucherClient.class, UserClient.class})
public class MerchandiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MerchandiseApplication.class, args);
    }

}
