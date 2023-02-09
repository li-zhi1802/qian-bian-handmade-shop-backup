package com.lmm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("star_shop")
@ApiModel(value = "StarShop对象", description = "")
public class StarShop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("店铺id")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty("收藏时间")
    @TableField("star_time")
    private LocalDateTime starTime;


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

    public LocalDateTime getStarTime() {
        return starTime;
    }

    public void setStarTime(LocalDateTime starTime) {
        this.starTime = starTime;
    }

    @Override
    public String toString() {
        return "StarShop{" +
                "userId=" + userId +
                ", shopId=" + shopId +
                ", starTime=" + starTime +
                "}";
    }
}
