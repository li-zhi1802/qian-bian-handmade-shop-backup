package com.lmm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("shipping_address")
@ApiModel(value = "ShippingAddress对象", description = "")
public class ShippingAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("发货地址的id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("关联的店铺id")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty("发货人姓名")
    @TableField("consignor_name")
    private String consignorName;

    @ApiModelProperty("发货人电话")
    @TableField("consignor_phone")
    private String consignorPhone;

    @ApiModelProperty("省")
    @TableField("province")
    private String province;

    @ApiModelProperty("县")
    @TableField("county")
    private String county;

    @ApiModelProperty("市")
    @TableField("city")
    private String city;

    @ApiModelProperty("街道")
    @TableField("street")
    private String street;

    @ApiModelProperty("发货地址优先级，0为默认发货地址")
    @TableField("priority")
    private Integer priority;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }

    public String getConsignorPhone() {
        return consignorPhone;
    }

    public void setConsignorPhone(String consignorPhone) {
        this.consignorPhone = consignorPhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", consignorName=" + consignorName +
                ", consignorPhone=" + consignorPhone +
                ", province=" + province +
                ", county=" + county +
                ", city=" + city +
                ", street=" + street +
                ", priority=" + priority +
                "}";
    }
}
