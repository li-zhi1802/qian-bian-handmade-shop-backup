package com.lmm.controller;

import com.lmm.dto.PageResult;
import com.lmm.dto.RestResult;
import com.lmm.service.StarShopService;
import com.lmm.util.SecurityUtil;
import com.lmm.vo.StarShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "收藏商铺的相关接口")
@RestController
@RequestMapping("/star-shop")
public class StarShopController {
    @Autowired
    private StarShopService starShopService;

    @ApiOperation("将商品添加至收藏列表或者在收藏列表中删除此商品")
    @PutMapping("/{shopId}")
    public RestResult starShop(@PathVariable("shopId") Long shopId) {
        return RestResult.success(starShopService.addOrRemoveShop(shopId, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("分页得到登录用户所有的收藏的商品")
    @GetMapping("/{pageNum}")
    public PageResult<StarShopVO> listStarShopByPage(@PathVariable("pageNum") Long pageNum) {
        return starShopService.listStarShopByPage(pageNum, SecurityUtil.getUser().getId());
    }

    @ApiOperation("删除指定收藏商品")
    @DeleteMapping
    public RestResult deleteStarShops(@RequestBody List<Long> shopId) {
        return RestResult.success(starShopService.deleteStarShops(shopId, SecurityUtil.getUser().getId()));
    }
}
