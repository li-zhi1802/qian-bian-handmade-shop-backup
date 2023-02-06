package com.lmm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@TableName("merchandise_comment")
@ApiModel(value = "MerchandiseComment对象", description = "")
public class MerchandiseComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论的id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联的用户")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("发布时间")
    @TableField("publish_time")
    private LocalDateTime publishTime;

    @ApiModelProperty("评论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("关联的商品id")
    @TableField("merchandise_id")
    private Long merchandiseId;

    @ApiModelProperty("关联的订单id")
    @TableField("order_id")
    private String orderId;

    @ApiModelProperty("父评论id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("商品真实性评分")
    @TableField("description_score")
    private BigDecimal descriptionScore;

    @ApiModelProperty("服务评分")
    @TableField("service_score")
    private BigDecimal serviceScore;

    @ApiModelProperty("物流评分")
    @TableField("logistics_score")
    private BigDecimal logisticsScore;

    @ApiModelProperty("评论关联的图片uri数组（JSON）")
    @TableField("pic_uris")
    private String picUris;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(Long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(BigDecimal descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public BigDecimal getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(BigDecimal serviceScore) {
        this.serviceScore = serviceScore;
    }

    public BigDecimal getLogisticsScore() {
        return logisticsScore;
    }

    public void setLogisticsScore(BigDecimal logisticsScore) {
        this.logisticsScore = logisticsScore;
    }

    public String getPicUris() {
        return picUris;
    }

    public void setPicUris(String picUris) {
        this.picUris = picUris;
    }

    @Override
    public String toString() {
        return "MerchandiseComment{" +
                "id=" + id +
                ", userId=" + userId +
                ", publishTime=" + publishTime +
                ", content=" + content +
                ", merchandiseId=" + merchandiseId +
                ", orderId=" + orderId +
                ", parentId=" + parentId +
                ", descriptionScore=" + descriptionScore +
                ", serviceScore=" + serviceScore +
                ", logisticsScore=" + logisticsScore +
                ", picUris=" + picUris +
                "}";
    }
}
