package com.lmm.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRegisterFormDTO {
    private String phone;
    private String password;
    private String nickName;
    private Integer age;
    private String email;
    private Integer sex;
    private String name;
    private LocalDateTime birthday;
}
