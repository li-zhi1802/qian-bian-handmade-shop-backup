package com.lmm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-10 10:38
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {
    @ApiModelProperty("店铺编号")
    private Long id;
    @ApiModelProperty("店铺名")
    private String name;
    @ApiModelProperty("店铺头像")
    private String avatarUri;
    @ApiModelProperty("该店铺下有哪些商品被加入购物车")
    private List<CartItem> cartItems;
    @ApiModelProperty("该店铺下有哪些优惠券")
    private List<VoucherVO> vouchers;
}
