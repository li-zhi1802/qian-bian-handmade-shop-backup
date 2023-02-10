package com.lmm.client;


import com.lmm.entity.Voucher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("qian-bian-voucher")
@RequestMapping("/voucher/feign")
public interface VoucherClient {
    @GetMapping("/{shopId}/{pageNum}")
    List<Voucher> listShopVouchers(@PathVariable("shopId") Long shopId, @PathVariable("pageNum") Long pageNum);
}
