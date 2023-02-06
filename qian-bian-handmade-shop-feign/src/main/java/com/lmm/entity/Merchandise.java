package com.lmm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@TableName("merchandise")
@ApiModel(value = "Merchandise对象", description = "")
public class Merchandise implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("归属店铺id")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty("商品名")
    @TableField("name")
    private String name;

    @ApiModelProperty("是否上架，1为上架，0为下架")
    @TableField("valid")
    private Integer valid;

    @ApiModelProperty("商品的视频地址")
    @TableField("video_uri")
    private String videoUri;

    @ApiModelProperty("商品单价")
    @TableField("unit_price")
    private BigDecimal unitPrice;

    @ApiModelProperty("归属哪个分类")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty("月销")
    @TableField("sales")
    private Integer sales;

    @ApiModelProperty("库存")
    @TableField("stock")
    private Integer stock;

    @ApiModelProperty("图片列表")
    @TableField("pic_uris")
    private String picUris;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPicUris() {
        return picUris;
    }

    public void setPicUris(String picUris) {
        this.picUris = picUris;
    }

    @Override
    public String toString() {
        return "Merchandise{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", name=" + name +
                ", valid=" + valid +
                ", videoUri=" + videoUri +
                ", unitPrice=" + unitPrice +
                ", categoryId=" + categoryId +
                ", sales=" + sales +
                ", stock=" + stock +
                ", picUris=" + picUris +
                "}";
    }
}
