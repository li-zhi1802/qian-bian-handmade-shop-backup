package com.lmm.client;

import com.lmm.entity.Merchandise;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("qian-bian-merchandise")
@RequestMapping("/merchandise")
public interface MerchandiseClient {
    /**
     * 得到商品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/feign/detail/{id}")
    Merchandise getMerchandiseById(@PathVariable("id") Long id);
}
