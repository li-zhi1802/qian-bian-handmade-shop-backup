package com.lmm.controller;

import com.lmm.dto.AddVoucherDTO;
import com.lmm.dto.RestResult;
import com.lmm.dto.UpdateVoucherDTO;
import com.lmm.service.VoucherService;
import com.lmm.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "商家管理优惠券的接口")
@RestController
@RequestMapping("/shop-keeper")
public class ShopKeeperVoucher {
    @Autowired
    private VoucherService voucherService;

    @ApiOperation("新增优惠券")
    @PostMapping
    public RestResult addVoucher(@RequestBody AddVoucherDTO addVoucherDTO) {
        return RestResult.success(voucherService.addVoucher(addVoucherDTO, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("修改指定优惠券的信息")
    @PutMapping
    public RestResult updateVoucher(@RequestBody UpdateVoucherDTO updateVoucherDTO) {
        return RestResult.success(voucherService.updateVoucher(updateVoucherDTO, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("发放指定优惠券")
    @PutMapping("/{voucherId}")
    public RestResult distributionVoucher(@PathVariable("voucherId") Long voucherId) {
        return RestResult.success(voucherService.distributionVoucher(voucherId, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("/删除优惠券")
    @DeleteMapping("/{voucherId}")
    public RestResult deleteVoucher(@PathVariable("voucherId") Long voucherId) {
        return RestResult.success(voucherService.removeById(voucherId));
    }

}
