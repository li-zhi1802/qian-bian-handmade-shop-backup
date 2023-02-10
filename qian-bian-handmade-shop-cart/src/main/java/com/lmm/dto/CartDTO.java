package com.lmm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 芝麻
 * @date : 2023-02-10 10:16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long merchandiseId;
    private Integer quantity;
}
