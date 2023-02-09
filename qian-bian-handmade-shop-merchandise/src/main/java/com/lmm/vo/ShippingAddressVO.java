package com.lmm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : 芝麻
 * @date : 2023-02-09 15:16
 **/
@Data
public class ShippingAddressVO {
    @ApiModelProperty("省")
    private String province;
    @ApiModelProperty("县")
    private String city;
    @ApiModelProperty("市")
    private String county;
}
