package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.dto.RestResult;
import com.lmm.dto.UserRegisterFormDTO;
import com.lmm.entity.UserInfo;
import com.lmm.mapper.UserInfoMapper;
import com.lmm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RestResult register(UserRegisterFormDTO registerFormDTO) {
        UserInfo registerUserInfo = BeanUtil.copyProperties(registerFormDTO, UserInfo.class);
        registerUserInfo.setPassword(passwordEncoder.encode(registerUserInfo.getPassword()));
        return RestResult.success(
                save(registerUserInfo)
        );
    }

    @Override
    public RestResult updatePassword(String password, Long userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setPassword(passwordEncoder.encode(password));
        return RestResult.success(
                updateById(userInfo)
        );
    }

    @Override
    public RestResult passwordIsRight(String password, Long userId) {
        String rightPassword = lambdaQuery().eq(UserInfo::getId, userId).select(UserInfo::getPassword).one().getPassword();
        return RestResult.success(
                // 判断是否正确
                passwordEncoder.matches(password, rightPassword)
        );
    }
}
