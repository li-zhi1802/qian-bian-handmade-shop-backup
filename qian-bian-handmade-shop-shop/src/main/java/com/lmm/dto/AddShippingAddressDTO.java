package com.lmm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 芝麻
 * @date : 2023-02-10 17:29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddShippingAddressDTO {
    private Long shopId;
    private String consignorName;
    private String consignorPhone;
    private String province;
    private String county;
    private String city;
    private String street;
}
