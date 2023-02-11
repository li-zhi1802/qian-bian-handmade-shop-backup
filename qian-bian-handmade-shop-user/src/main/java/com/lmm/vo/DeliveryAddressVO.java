package com.lmm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 芝麻
 * @date : 2023-02-11 08:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddressVO {
    private Integer id;
    private String consigneeName;
    private String consigneePhone;
    private String province;
    private String county;
    private String city;
    private String street;
}
