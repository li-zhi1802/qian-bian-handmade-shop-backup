package com.lmm.controller;

import com.lmm.dto.UserDTO;
import com.lmm.util.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/footmark")
public class FootmarkController {
    @GetMapping("test")
    public UserDTO test() {
        UserDTO user = SecurityUtil.getUser();
        return user == null ? new UserDTO(0L, "baocuo", "报错") : user;
    }
}
