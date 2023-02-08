package com.lmm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lmm.dto.RestResult;
import com.lmm.dto.UserRegisterFormDTO;
import com.lmm.dto.UserUpdateFormDTO;
import com.lmm.entity.UserInfo;
import com.lmm.service.UserInfoService;
import com.lmm.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public RestResult register(@RequestBody UserRegisterFormDTO registerFormDTO) {
        return userInfoService.register(registerFormDTO);
    }

    @ApiOperation("更新用户基本信息（除了头像和密码都可以更新）")
    @PutMapping("/basic")
    public RestResult updateUserInfo(@RequestBody UserUpdateFormDTO userUpdateFormDTO) {
        UserInfo userInfo = BeanUtil.copyProperties(userUpdateFormDTO, UserInfo.class);
        userInfo.setId(SecurityUtil.getUser().getId());
        return RestResult.success(
                userInfoService.updateById(userInfo)
        );
    }

    @ApiOperation("修改密码")
    @PutMapping("/password")
    public RestResult updatePassword(@RequestBody String password) {
        return userInfoService.updatePassword(password, SecurityUtil.getUser().getId());
    }

    @ApiOperation("判断密码是否正确")
    @GetMapping("/isRight")
    public RestResult passwordIsRight(@RequestBody String password) {
        return userInfoService.passwordIsRight(password, SecurityUtil.getUser().getId());
    }
}
