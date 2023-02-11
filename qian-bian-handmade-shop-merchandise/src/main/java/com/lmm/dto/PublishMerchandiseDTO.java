package com.lmm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-11 09:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishMerchandiseDTO {
    @ApiModelProperty("商铺id")
    private Long shopId;
    @ApiModelProperty("商品名")
    private String name;
    @ApiModelProperty("商品视频地址，前端需要先上传视频，然后将返回的视频地址再此上传")
    private String videoUri;
    @ApiModelProperty("商品单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("商品归属分类")
    private Integer categoryId;
    @ApiModelProperty("商品初始库存")
    private Integer stock;
    @ApiModelProperty("商品图片列表")
    private List<String> picUris;
}
