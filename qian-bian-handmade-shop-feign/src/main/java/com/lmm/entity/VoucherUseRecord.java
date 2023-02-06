package com.lmm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@TableName("voucher_use_record")
@ApiModel(value = "VoucherUseRecord对象", description = "")
public class VoucherUseRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("优惠券id")
    @TableField("voucher_id")
    private Long voucherId;

    @ApiModelProperty("关联的订单id")
    @TableField("order_id")
    private String orderId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "VoucherUseRecord{" +
                "userId=" + userId +
                ", voucherId=" + voucherId +
                ", orderId=" + orderId +
                "}";
    }
}
