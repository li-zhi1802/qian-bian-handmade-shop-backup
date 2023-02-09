package com.lmm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@TableName("star_merchandise")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "StarMerchandise对象", description = "")
public class StarMerchandise implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("商品id")
    @TableField("merchandise_id")
    private Long merchandiseId;

    @ApiModelProperty("收藏时间")
    @TableField("star_time")
    private LocalDateTime starTime;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(Long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public LocalDateTime getStarTime() {
        return starTime;
    }

    public void setStarTime(LocalDateTime starTime) {
        this.starTime = starTime;
    }

    @Override
    public String toString() {
        return "StarMerchandise{" +
                "userId=" + userId +
                ", merchandiseId=" + merchandiseId +
                ", starTime=" + starTime +
                "}";
    }
}
