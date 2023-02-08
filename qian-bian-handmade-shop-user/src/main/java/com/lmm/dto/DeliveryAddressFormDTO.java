package com.lmm.dto;

import lombok.Data;

/**
 * @author : 芝麻
 * @date : 2023-02-08 17:38
 **/
@Data
public class DeliveryAddressFormDTO {
    private String consigneeName;
    private String consigneePhone;
    private String province;
    private String county;
    private String city;
    private String street;
}
