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
@TableName("footmark")
@ApiModel(value = "Footmark对象", description = "")
public class Footmark implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户编号")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    @ApiModelProperty("商品编号")
    @TableField("merchandise_id")
    private Long merchandiseId;

    @ApiModelProperty("上一次浏览的时间，用来排序")
    @TableField("last_look_time")
    private LocalDateTime lastLookTime;


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

    public LocalDateTime getLastLookTime() {
        return lastLookTime;
    }

    public void setLastLookTime(LocalDateTime lastLookTime) {
        this.lastLookTime = lastLookTime;
    }

    @Override
    public String toString() {
        return "Footmark{" +
                "userId=" + userId +
                ", merchandiseId=" + merchandiseId +
                ", lastLookTime=" + lastLookTime +
                "}";
    }
}
