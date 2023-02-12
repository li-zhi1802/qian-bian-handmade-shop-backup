package com.lmm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchandiseVO {
    private Long id;
    private String name;
    private String picUri;
    private BigDecimal unitPrice;
    private Integer quantity;
}
