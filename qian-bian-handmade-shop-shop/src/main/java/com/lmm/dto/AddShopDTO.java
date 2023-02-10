package com.lmm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 芝麻
 * @date : 2023-02-10 16:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddShopDTO {
    private String name;
    private String avatarUri;
}
