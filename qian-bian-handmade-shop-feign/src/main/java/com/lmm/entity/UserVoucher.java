package com.lmm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("user_voucher")
@ApiModel(value = "UserVoucher对象", description = "")
public class UserVoucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("优惠券id")
    @TableField("voucher_id")
    private Long voucherId;

    @ApiModelProperty("领取时间")
    @TableField("get_time")
    private LocalDateTime getTime;

    @ApiModelProperty("用户拥有的优惠券状态")
    @TableField("state")
    private String state;


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

    public LocalDateTime getGetTime() {
        return getTime;
    }

    public void setGetTime(LocalDateTime getTime) {
        this.getTime = getTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
