package com.lmm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-12 10:38
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchandiseOrderVO {
    private String id;

    private String state;
    private String deliveryAddress;
    private BigDecimal total;
    private BigDecimal actualTotal;
    private LocalDateTime payTime;
    private LocalDateTime createdTime;

    private ShopVO shop;

    private List<MerchandiseVO> merchandises;
}
