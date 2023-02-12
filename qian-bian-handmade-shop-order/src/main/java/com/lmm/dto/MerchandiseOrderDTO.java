package com.lmm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-12 09:53
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchandiseOrderDTO {
    private Long shopId;
    private BigDecimal total;
    private BigDecimal actualTotal;
    private Long voucherId;
    private String deliveryAddress;
    private List<MerchandiseItemDTO> merchandises;
}