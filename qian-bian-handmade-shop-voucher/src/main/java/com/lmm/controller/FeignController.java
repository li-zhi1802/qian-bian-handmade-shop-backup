package com.lmm.controller;

import com.lmm.entity.Voucher;
import com.lmm.service.VoucherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-10 14:22
 **/
@Api(tags = "远程调用的接口")
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

    @ApiOperation("使用数目加一")
    @PostMapping("/{voucherId}")
    public Boolean increaseUsedAmount(@PathVariable("voucherId") Long voucherId) {
        return voucherService.lambdaUpdate()
                .eq(Voucher::getId, voucherId)
                // 更新使用的数目
                .last("set used_amount = used_amount + 1")
                .update();
    }
}
