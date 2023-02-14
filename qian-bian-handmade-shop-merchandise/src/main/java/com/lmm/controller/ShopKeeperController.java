package com.lmm.controller;

import com.lmm.dto.PublishMerchandiseDTO;
import com.lmm.dto.RestResult;
import com.lmm.dto.UpdateMerchandiseDTO;
import com.lmm.service.MerchandiseService;
import com.lmm.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : 芝麻
 * @date : 2023-02-11 09:20
 **/
@Api(tags = "商家管理商品的接口")
@RestController
@RequestMapping("/shop-keeper")
public class ShopKeeperController {
    @Autowired
    private MerchandiseService merchandiseService;

    @ApiOperation("发布商品")
    @PostMapping
    public RestResult publishMerchandise(@RequestBody PublishMerchandiseDTO publishMerchandiseDTO) {
        return RestResult.success(merchandiseService.publishMerchandise(publishMerchandiseDTO, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("修改商品信息")
    @PutMapping
    public RestResult updateMerchandise(@RequestBody UpdateMerchandiseDTO updateMerchandiseDTO) {
        return RestResult.success(merchandiseService.updateMerchandise(updateMerchandiseDTO));
    }

    @ApiOperation("下架商品")
    @PutMapping("/lowerShelf/{merchandiseId}")
    public RestResult lowerShelfMerchandise(@PathVariable("merchandiseId") Long merchandiseId) {
        return RestResult.success(merchandiseService.lowerShelfMerchandise(merchandiseId));
    }

}
