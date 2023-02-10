package com.lmm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShippingAddressVO {
    @ApiModelProperty("省")
    private String province;
    @ApiModelProperty("县")
    private String county;
    @ApiModelProperty("市")
    private String city;
}
