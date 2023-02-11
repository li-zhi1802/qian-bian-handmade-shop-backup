package com.lmm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : 芝麻
 * @date : 2023-02-11 08:00
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Long id;
    private String phone;
    private String avatarUri;
    private String nickName;
    private Integer age;
    private String email;
    private Integer sex;
    private LocalDateTime birthday;
    private DeliveryAddressVO defaultDeliveryAddress;
    private String name;
}
