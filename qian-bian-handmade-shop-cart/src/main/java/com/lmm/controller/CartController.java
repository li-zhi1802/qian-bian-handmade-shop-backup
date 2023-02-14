package com.lmm.controller;

import com.lmm.dto.DeleteCartItemDTO;
import com.lmm.dto.RestResult;
import com.lmm.service.CartService;
import com.lmm.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Api(tags = "购物车的相关接口")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @ApiOperation("在购物车中增加一个商品，如果已经存在就数量加1")
    @PutMapping("/{shopId}/{merchandiseId}")
    public RestResult addMerchandiseToCart(@PathVariable("shopId") Long shopId, @PathVariable("merchandiseId") Long merchandiseId) {
        return RestResult.success(cartService.addMerchandiseToCart(shopId, merchandiseId, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("删除购物车中的一件商品")
    @DeleteMapping("/{shopId}/{merchandiseId}")
    public RestResult removeMerchandiseFromCart(@PathVariable("shopId") Long shopId, @PathVariable("merchandiseId") Long merchandiseId) {
        return RestResult.success(cartService.removeMerchandiseFromCart(shopId, merchandiseId, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("批量删除购物车中的商品")
    @DeleteMapping
    public RestResult removeMerchandisesFromCart(@RequestBody List<DeleteCartItemDTO> deleteCartItemDTOs) {
        Long userId = SecurityUtil.getUser().getId();
        boolean success = true;
        for (DeleteCartItemDTO deleteCartItemDTO : deleteCartItemDTOs) {
            for (Long merchandiseId : deleteCartItemDTO.getMerchandiseIds()) {
                // 将每次的结果都和success做与运算，所有的删除操作都是true，success才是true
                success &= cartService.removeMerchandiseFromCart(deleteCartItemDTO.getShopId(), merchandiseId, userId);
            }
        }
        return RestResult.success(success);
    }

    @ApiOperation("得到当前登录用户的购物车")
    @GetMapping
    public RestResult getMyCart() {
        return RestResult.success(cartService.getMyCart(SecurityUtil.getUser().getId()));
    }

    @ApiOperation("修改购物车商品数量")
    @PutMapping("/{shopId}/{merchandiseId}/{quantity}")
    public RestResult updateMerchandiseQuantity(@PathVariable("shopId") Long shopId, @PathVariable("merchandiseId") Long merchandiseId, @PathVariable("quantity") Integer quantity) {
        return RestResult.success(cartService.updateMerchandiseQuantity(shopId, merchandiseId, quantity, SecurityUtil.getUser().getId()));
    }
}
