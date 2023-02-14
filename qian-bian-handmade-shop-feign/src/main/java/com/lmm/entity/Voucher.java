package com.lmm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@TableName("voucher")
@ApiModel(value = "Voucher对象", description = "")
public class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("优惠券的id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;

    @ApiModelProperty("关联的店铺编号")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty("标题，满min_money减decrease_money券")
    @TableField("title")
    private String title;

    @ApiModelProperty("最少达到多少钱才可以用")
    @TableField("min_money")
    private Integer minMoney;

    @ApiModelProperty("减多少钱（如果是打折券，这里就是几折）")
    @TableField("decrease_money")
    private Integer decreaseMoney;

    @ApiModelProperty("每个人限领几张")
    @TableField("`limit`")
    private Integer limit;

    @ApiModelProperty("配额")
    @TableField("quota")
    private Integer quota;

    @ApiModelProperty("库存")
    @TableField("stock")
    private Integer stock;

    @ApiModelProperty("已经被领取的数目")
    @TableField("take_amount")
    private Integer takeAmount;

    @ApiModelProperty("已经被使用的数目")
    @TableField("used_amount")
    private Integer usedAmount;

    @ApiModelProperty("发放的开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @ApiModelProperty("发放的结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("有效期开始时间")
    @TableField("valid_use_start_time")
    private LocalDateTime validUseStartTime;

    @ApiModelProperty("有效期结束时间")
    @TableField("valid_use_end_time")
    private LocalDateTime validUseEndTime;

    @ApiModelProperty("0暂未生效（还不在有效时间）1有效 2强制失效（商家在优惠券有效时间内自己取消）3失效（当前时间已经在有效结束时间之后了）")
    @TableField("state")
    private String state;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(Integer minMoney) {
        this.minMoney = minMoney;
    }

    public Integer getDecreaseMoney() {
        return decreaseMoney;
    }

    public void setDecreaseMoney(Integer decreaseMoney) {
        this.decreaseMoney = decreaseMoney;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getTakeAmount() {
        return takeAmount;
    }

    public void setTakeAmount(Integer takeAmount) {
        this.takeAmount = takeAmount;
    }

    public Integer getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(Integer usedAmount) {
        this.usedAmount = usedAmount;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getValidUseStartTime() {
        return validUseStartTime;
    }

    public void setValidUseStartTime(LocalDateTime validUseStartTime) {
        this.validUseStartTime = validUseStartTime;
    }

    public LocalDateTime getValidUseEndTime() {
        return validUseEndTime;
    }

    public void setValidUseEndTime(LocalDateTime validUseEndTime) {
        this.validUseEndTime = validUseEndTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id=" + id +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", shopId=" + shopId +
                ", title=" + title +
                ", minMoney=" + minMoney +
                ", decreaseMoney=" + decreaseMoney +
                ", limit=" + limit +
                ", quota=" + quota +
                ", stock=" + stock +
                ", takeAmount=" + takeAmount +
                ", usedAmount=" + usedAmount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", validUseStartTime=" + validUseStartTime +
                ", validUseEndTime=" + validUseEndTime +
                ", state=" + state +
                "}";
    }
}
