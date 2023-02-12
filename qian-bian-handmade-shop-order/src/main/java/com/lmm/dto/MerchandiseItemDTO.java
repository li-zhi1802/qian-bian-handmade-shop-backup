package com.lmm.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : 芝麻
 * @date : 2023-02-12 10:03
 **/
@Data
public class MerchandiseItemDTO {
    private Long id;
    private Integer quantity;
    private BigDecimal unitPrice;
}
