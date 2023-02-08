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
@TableName("merchandise_order")
@ApiModel(value = "MerchandiseOrder对象", description = "")
public class MerchandiseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("关联的商铺id")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty("[{\"id\":xx,\"quantity\":xx,\"unitPrice\":xx.xx}]")
    @TableField("merchandises")
    private String merchandises;

    @ApiModelProperty("订单状态（-2订单取消-1待支付，0已付款，1待发货，2待收货，3待评价，4订单完成，5售后中，6退款成功）")
    @TableField("state")
    private String state;

    @ApiModelProperty("收货地址")
    @TableField("delivery_address")
    private String deliveryAddress;

    @ApiModelProperty("订单创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty("总金额")
    @TableField("total")
    private BigDecimal total;

    @ApiModelProperty("实际付款")
    @TableField("actual_total")
    private BigDecimal actualTotal;

    @ApiModelProperty("优惠券id")
    @TableField("voucher_id")
    private Long voucherId;

    @ApiModelProperty("支付时间")
    @TableField("pay_time")
    private LocalDateTime payTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMerchandises() {
        return merchandises;
    }

    public void setMerchandises(String merchandises) {
        this.merchandises = merchandises;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(BigDecimal actualTotal) {
        this.actualTotal = actualTotal;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "MerchandiseOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", shopId=" + shopId +
                ", merchandises=" + merchandises +
                ", state=" + state +
                ", deliveryAddress=" + deliveryAddress +
                ", createdTime=" + createdTime +
                ", total=" + total +
                ", actualTotal=" + actualTotal +
                ", voucherId=" + voucherId +
                ", payTime=" + payTime +
                "}";
    }
}
