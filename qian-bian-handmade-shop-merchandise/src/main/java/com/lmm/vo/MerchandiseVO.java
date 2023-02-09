package com.lmm.vo;

import com.lmm.entity.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-09 15:00
 **/
@Data
public class MerchandiseVO {
    @ApiModelProperty("商品id")
    private Long id;
    @ApiModelProperty("商品名")
    private String name;
    @ApiModelProperty("商品介绍视频")
    private String videoUri;
    @ApiModelProperty("商品单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("商品月销售额")
    private Integer sales;
    @ApiModelProperty("商品库存")
    private Integer stock;
    @ApiModelProperty("商品图片")
    private List<String> picUris;
    @ApiModelProperty("商品归属的商铺信息")
    private ShopVO shop;
    @ApiModelProperty("商品类目的信息")
    private Category category;
    @ApiModelProperty("商品关联的优惠券")
    private List<VoucherVO> vouchers;
    @ApiModelProperty("商品关联的评论")
    private List<MerchandiseCommentVO> comments;
}
