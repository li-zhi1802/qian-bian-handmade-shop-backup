package com.lmm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-11 09:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishMerchandiseCommentDTO {
    private String content;
    private Long merchandiseId;
    private String orderId;
    private BigDecimal descriptionScore;
    private BigDecimal serviceScore;
    private BigDecimal logisticsScore;
    private List<String> picUris;
}
