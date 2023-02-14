package com.lmm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lmm.dto.DeliveryAddressFormDTO;
import com.lmm.dto.RestResult;
import com.lmm.entity.DeliveryAddress;
import com.lmm.service.DeliveryAddressService;
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
@Api(tags = "管理收货地址的接口")
@RestController
@RequestMapping("/delivery-address")
public class DeliveryAddressController {
    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @ApiOperation("得到登录用户的默认收货地址")
    @GetMapping("/default")
    public RestResult getDefaultDeliveryAddress() {
        return RestResult.success(deliveryAddressService.getDefaultDeliveryAddress(SecurityUtil.getUser().getId()));
    }

    @ApiOperation("获取所有的收货地址")
    @GetMapping
    public RestResult listAllDeliveryAddresses() {
        return RestResult.success(deliveryAddressService.listAllDeliveryAddresses(SecurityUtil.getUser().getId()));
    }

    @ApiOperation("修改收货地址基本信息")
    @PutMapping("/{deliveryAddressId}")
    public RestResult updateDeliveryAddress(@RequestBody DeliveryAddressFormDTO deliveryAddressFormDTO, @PathVariable("deliveryAddressId") Long deliveryAddressId) {
        DeliveryAddress deliveryAddress = BeanUtil.copyProperties(deliveryAddressFormDTO, DeliveryAddress.class);
        deliveryAddress.setId(deliveryAddressId);
        return RestResult.success(
                deliveryAddressService.saveOrUpdate(
                        deliveryAddress
                )
        );
    }

    @ApiOperation("更新默认地址")
    @PutMapping("/default/{deliveryAddressId}/{priority}")
    public RestResult updateDefaultDeliveryAddress(@PathVariable("deliveryAddressId") Long deliveryAddressId, @PathVariable("priority") Integer priority) {
        return RestResult.success(deliveryAddressService.updateDefaultDeliveryAddress(SecurityUtil.getUser().getId(), deliveryAddressId, priority));
    }

    @ApiOperation("增加收货地址")
    @PostMapping
    public RestResult addDeliveryAddress(@RequestBody DeliveryAddressFormDTO deliveryAddressFormDTO) {
        return RestResult.success(deliveryAddressService.saveDeliveryAddress(deliveryAddressFormDTO, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("删除收货地址，如果是默认地址被删除，则优先级为1的收货地址顶上")
    @DeleteMapping("/{deliveryAddressId}/{priority}")
    public RestResult deleteDeliveryAddress(@PathVariable("deliveryAddressId") Long deliveryAddressId, @PathVariable("priority") Integer priority) {
        return RestResult.success(deliveryAddressService.deleteDeliveryAddress(deliveryAddressId, priority, SecurityUtil.getUser().getId()));
    }
}
