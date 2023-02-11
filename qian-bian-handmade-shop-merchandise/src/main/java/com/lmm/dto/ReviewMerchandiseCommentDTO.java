package com.lmm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 芝麻
 * @date : 2023-02-11 09:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewMerchandiseCommentDTO {
    private String content;
    private Long merchandiseId;
    private Long parentId;
}
