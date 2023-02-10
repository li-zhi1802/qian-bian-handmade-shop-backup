package com.lmm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author : 芝麻
 * @date : 2023-02-10 10:40
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @ApiModelProperty("商品编号")
    private Long id;
    @ApiModelProperty("商品名")
    private String name;
    @ApiModelProperty("商品图标")
    private String avatarUri;
    @ApiModelProperty("商品单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("加入购物车的商品数量")
    private Integer quantity;
}
