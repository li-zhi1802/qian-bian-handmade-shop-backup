package com.lmm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FootmarkVO {
    @ApiModelProperty("商品id")
    private Long id;
    @ApiModelProperty("商品名称")
    private String name;
    @ApiModelProperty("商品单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("商品图标")
    private String picUri;
    @ApiModelProperty("商品是否有效")
    private Integer valid;
    @ApiModelProperty("商品归属店铺")
    private Long shopId;
}
