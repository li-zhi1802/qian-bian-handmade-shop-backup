package com.lmm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("delivery_address")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DeliveryAddress对象", description = "")
public class DeliveryAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收货地址id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户编号")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("收货人姓名")
    @TableField("consignee_name")
    private String consigneeName;

    @ApiModelProperty("收货人电话")
    @TableField("consignee_phone")
    private String consigneePhone;

    @ApiModelProperty("省")
    @TableField("province")
    private String province;

    @ApiModelProperty("县")
    @TableField("county")
    private String county;

    @ApiModelProperty("市")
    @TableField("city")
    private String city;

    @ApiModelProperty("具体街道")
    @TableField("street")
    private String street;

    @ApiModelProperty("地址优先级，为0则是默认地址")
    @TableField("priority")
    private Integer priority;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
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
        return "DeliveryAddress{" +
                "id=" + id +
                ", userId=" + userId +
                ", consigneeName=" + consigneeName +
                ", consigneePhone=" + consigneePhone +
                ", province=" + province +
                ", county=" + county +
                ", city=" + city +
                ", street=" + street +
                ", priority=" + priority +
                "}";
    }
}
