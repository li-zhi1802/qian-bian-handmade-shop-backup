package com.lmm.controller;

import com.lmm.entity.Voucher;
import com.lmm.service.VoucherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-10 14:22
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private VoucherService voucherService;

    @ApiOperation("分页返回指定商家的优惠券")
    @GetMapping("/{shopId}/{pageNum}")
    public List<Voucher> listShopVouchers(@PathVariable("shopId") Long shopId, @PathVariable("pageNum") Long pageNum) {
        return voucherService.pageQueryVouchersByShopId(shopId, pageNum).getData();
    }
}
