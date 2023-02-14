package com.lmm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author : 芝麻
 * @date : 2023-02-09 15:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarMerchandiseVO {
    @ApiModelProperty("商品id")
    private Long id;
    @ApiModelProperty("店铺id")
    private Long shopId;
    @ApiModelProperty("商品名")
    private String name;
    @ApiModelProperty("商品是否上架")
    private Integer valid;
    @ApiModelProperty("商品单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("商品照片")
    private String picUris;
}
