package com.lmm.controller;

import com.lmm.dto.AddShopDTO;
import com.lmm.dto.RestResult;
import com.lmm.service.ShopService;
import com.lmm.util.SecurityUtil;
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
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @ApiOperation("返回指定店铺详情")
    @GetMapping("/{shopId}")
    public RestResult getShopDetailById(@PathVariable Long shopId) {
        return RestResult.success(shopService.getShopDetailById(shopId));
    }

    @ApiOperation("增加一个商铺")
    @PostMapping
    public RestResult addShop(@RequestBody AddShopDTO addShopDTO) {
        return RestResult.success(shopService.addShop(addShopDTO, SecurityUtil.getUser().getId()));
    }
}
