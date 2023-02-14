package com.lmm.dto;

import com.lmm.validation.Sex;
import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Data
public class UserRegisterFormDTO {
    private String phone;
    private String password;
    private String nickName;
    private Integer age;
    @Email
    private String email;
    @Sex
    private Integer sex;
    private String name;
    private LocalDateTime birthday;
}
