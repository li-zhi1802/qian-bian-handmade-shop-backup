package com.lmm.controller;

import com.lmm.dto.PageResult;
import com.lmm.dto.RestResult;
import com.lmm.entity.Voucher;
import com.lmm.service.VoucherService;
import com.lmm.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Api(tags = "商家和用户共同的优惠券接口")
@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @ApiOperation("分页返回指定商家的优惠券")
    @GetMapping("/{shopId}/{pageNum}")
    public PageResult<Voucher> listShopVouchers(@PathVariable("shopId") Long shopId, @PathVariable("pageNum") Long pageNum) {
        return voucherService.pageQueryVouchersByShopId(shopId, pageNum);
    }

    @ApiOperation("登录用户领取指定优惠券")
    @PostMapping("/{voucherId}")
    public RestResult receiveVoucher(@PathVariable("voucherId") Long voucherId) {
        return RestResult.success(voucherService.receiveVoucher(voucherId, SecurityUtil.getUser().getId()));
    }
}
