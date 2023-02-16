package com.lmm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 芝麻
 * @date : 2023-02-12 10:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopVO {
    private Long id;
    private String name;
    private String avatarUri;
}
