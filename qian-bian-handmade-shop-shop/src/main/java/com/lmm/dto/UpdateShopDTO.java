package com.lmm.dto;

import lombok.Data;

/**
 * @author : 芝麻
 * @date : 2023-02-10 17:09
 **/
@Data
public class UpdateShopDTO {
    private Long id;
    private String name;
    private String avatarUri;
    private Long defaultAddressId;
}
