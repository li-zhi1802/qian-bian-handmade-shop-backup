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
@TableName("cart")
@ApiModel(value = "Cart对象", description = "")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("商品id")
    @TableField("merchandise_id")
    private Long merchandiseId;

    @ApiModelProperty("商品数量")
    @TableField("quantity")
    private Integer quantity;


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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", merchandiseId=" + merchandiseId +
                ", quantity=" + quantity +
                "}";
    }
}
