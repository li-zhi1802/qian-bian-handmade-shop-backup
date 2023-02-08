package com.lmm.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : 芝麻
 * @date : 2023-02-08 18:13
 **/
@Data
public class UserUpdateFormDTO {
    private String phone;
    private String avatarUri;
    private String nickName;
    private Integer age;
    private String email;
    private Integer sex;
    private LocalDateTime birthday;
    private String name;
}
