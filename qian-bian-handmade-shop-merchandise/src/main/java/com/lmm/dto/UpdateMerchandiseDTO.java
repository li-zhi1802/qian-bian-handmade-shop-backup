package com.lmm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-11 09:41
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMerchandiseDTO {
    private Long id;
    private String name;
    private BigDecimal unitPrice;
    private Integer categoryId;
    private Integer stock;
    private String videoUri;
    private List<String> picUris;
}
