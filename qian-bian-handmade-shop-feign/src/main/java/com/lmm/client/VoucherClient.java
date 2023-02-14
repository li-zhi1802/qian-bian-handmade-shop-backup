package com.lmm.client;


import com.lmm.entity.Voucher;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(tags = "优惠券Feign客户端")
@FeignClient("qian-bian-voucher")
@RequestMapping("/voucher/feign")
public interface VoucherClient {
    @GetMapping("/{shopId}/{pageNum}")
    List<Voucher> listShopVouchers(@PathVariable("shopId") Long shopId, @PathVariable("pageNum") Long pageNum);

    @PostMapping("/{voucherId}")
    public Boolean increaseUsedAmount(@PathVariable("voucherId") Long voucherId);
}
