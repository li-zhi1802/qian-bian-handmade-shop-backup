package com.lmm;

import com.lmm.client.MerchandiseClient;
import com.lmm.client.ShopClient;
import com.lmm.client.VoucherClient;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableSwagger2Doc
@SpringBootApplication
@EnableFeignClients(clients = {ShopClient.class, MerchandiseClient.class, VoucherClient.class})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
