package com.lmm.vo;

import com.lmm.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author : 芝麻
 * @date : 2023-02-10 11:16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopVO {
    private Long id;
    private String name;
    private String avatarUri;
    private BigDecimal descriptionAvgScore;
    private BigDecimal serviceAvgScore;
    private BigDecimal logisticsAvgScore;
    private UserDTO shopKeeper;
    private ShippingAddressVO defaultAddress;
}
