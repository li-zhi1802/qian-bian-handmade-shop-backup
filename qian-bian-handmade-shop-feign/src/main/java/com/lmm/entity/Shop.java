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
@TableName("shop")
@ApiModel(value = "Shop对象", description = "")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商铺主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("店铺名")
    @TableField("name")
    private String name;

    @ApiModelProperty("商铺真实性打分")
    @TableField("description_avg_score")
    private BigDecimal descriptionAvgScore;

    @ApiModelProperty("服务打分")
    @TableField("service_avg_score")
    private BigDecimal serviceAvgScore;

    @ApiModelProperty("物流打分")
    @TableField("logistics_avg_score")
    private BigDecimal logisticsAvgScore;

    @ApiModelProperty("真实性打分总和")
    @TableField("description_score_sum")
    private BigDecimal descriptionScoreSum;

    @ApiModelProperty("服务打分总和")
    @TableField("service_score_sum")
    private BigDecimal serviceScoreSum;

    @ApiModelProperty("物流打分总和")
    @TableField("logistics_score_sum")
    private BigDecimal logisticsScoreSum;

    @ApiModelProperty("评论总数")
    @TableField("comment_sum")
    private Long commentSum;

    @ApiModelProperty("店铺的头像")
    @TableField("avatar_uri")
    private String avatarUri;

    @ApiModelProperty("店主编号")
    @TableField("shop_keeper_id")
    private Long shopKeeperId;

    @ApiModelProperty("默认发货地址")
    @TableField("default_address_id")
    private Long defaultAddressId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDescriptionAvgScore() {
        return descriptionAvgScore;
    }

    public void setDescriptionAvgScore(BigDecimal descriptionAvgScore) {
        this.descriptionAvgScore = descriptionAvgScore;
    }

    public BigDecimal getServiceAvgScore() {
        return serviceAvgScore;
    }

    public void setServiceAvgScore(BigDecimal serviceAvgScore) {
        this.serviceAvgScore = serviceAvgScore;
    }

    public BigDecimal getLogisticsAvgScore() {
        return logisticsAvgScore;
    }

    public void setLogisticsAvgScore(BigDecimal logisticsAvgScore) {
        this.logisticsAvgScore = logisticsAvgScore;
    }

    public BigDecimal getDescriptionScoreSum() {
        return descriptionScoreSum;
    }

    public void setDescriptionScoreSum(BigDecimal descriptionScoreSum) {
        this.descriptionScoreSum = descriptionScoreSum;
    }

    public BigDecimal getServiceScoreSum() {
        return serviceScoreSum;
    }

    public void setServiceScoreSum(BigDecimal serviceScoreSum) {
        this.serviceScoreSum = serviceScoreSum;
    }

    public BigDecimal getLogisticsScoreSum() {
        return logisticsScoreSum;
    }

    public void setLogisticsScoreSum(BigDecimal logisticsScoreSum) {
        this.logisticsScoreSum = logisticsScoreSum;
    }

    public Long getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(Long commentSum) {
        this.commentSum = commentSum;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public Long getShopKeeperId() {
        return shopKeeperId;
    }

    public void setShopKeeperId(Long shopKeeperId) {
        this.shopKeeperId = shopKeeperId;
    }

    public Long getDefaultAddressId() {
        return defaultAddressId;
    }

    public void setDefaultAddressId(Long defaultAddressId) {
        this.defaultAddressId = defaultAddressId;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name=" + name +
                ", descriptionAvgScore=" + descriptionAvgScore +
                ", serviceAvgScore=" + serviceAvgScore +
                ", logisticsAvgScore=" + logisticsAvgScore +
                ", descriptionScoreSum=" + descriptionScoreSum +
                ", serviceScoreSum=" + serviceScoreSum +
                ", logisticsScoreSum=" + logisticsScoreSum +
                ", commentSum=" + commentSum +
                ", avatarUri=" + avatarUri +
                ", shopKeeperId=" + shopKeeperId +
                ", defaultAddressId=" + defaultAddressId +
                "}";
    }
}
