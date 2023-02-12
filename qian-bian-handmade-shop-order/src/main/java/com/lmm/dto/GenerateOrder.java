package com.lmm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-12 10:00
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateOrder {
    private List<String> orderIds;
    private BigDecimal totalAmount;
}
