package com.lmm.client;

import com.lmm.entity.Merchandise;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(tags = "商品Feign客户端")
@FeignClient("qian-bian-merchandise")
@RequestMapping("/merchandise/feign")
public interface MerchandiseClient {
    /**
     * 得到商品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Merchandise getMerchandiseById(@PathVariable("id") Long id);

    /**
     * 返回指定商铺的几个商品
     *
     * @param shopId
     * @return
     */
    @GetMapping("/randomMerchandise/{shopId}")
    List<Merchandise> randomMerchandiseByShopId(@PathVariable("shopId") Long shopId);
}
