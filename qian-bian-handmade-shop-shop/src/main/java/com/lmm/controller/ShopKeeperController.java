package com.lmm.controller;

import com.lmm.dto.AddShippingAddressDTO;
import com.lmm.dto.RestResult;
import com.lmm.dto.UpdateShopDTO;
import com.lmm.service.ShippingAddressService;
import com.lmm.service.ShopService;
import com.lmm.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/shop-keeper")
@RestController
public class ShopKeeperController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShippingAddressService shippingAddressService;

    @ApiOperation("更改商铺信息")
    @PutMapping("/shop")
    public RestResult updateShopInfo(@RequestBody UpdateShopDTO updateShopDTO) {
        return RestResult.success(shopService.updateShopInfo(updateShopDTO, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("新增发货地址")
    @PostMapping("/shipping-address")
    public RestResult addShippingAddress(@RequestBody AddShippingAddressDTO addShippingAddressDTO) {
        return RestResult.success(shippingAddressService.addShippingAddress(addShippingAddressDTO, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("删除发货地址")
    @DeleteMapping("/shipping-address/{shopId}/{shippingAddressId}")
    public RestResult deleteShippingAddress(@PathVariable("shopId") Long shopId, @PathVariable("shippingAddressId") Long shippingAddressId) {
        return RestResult.success(shippingAddressService.deleteShippingAddress(shopId, shippingAddressId, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("查询发货地址")
    @GetMapping("/shipping-address/{shopId}")
    public RestResult allShippingAddress(@PathVariable("shopId") Long shopId) {
        return RestResult.success(shippingAddressService.allShippingAddress(shopId, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("修改默认发货地址")
    @GetMapping("/shipping-address/default/{shopId}/{shippingAddressId}/{priority}")
    public RestResult updateDefaultShippingAddress(@PathVariable("shopId") Long shopId,
                                                   @PathVariable("shippingAddressId") Long shippingAddressId,
                                                   @PathVariable("priority") Integer priority) {
        return RestResult.success(shippingAddressService.updateDefaultShippingAddress(shopId, shippingAddressId, priority, SecurityUtil.getUser().getId()));
    }
}
