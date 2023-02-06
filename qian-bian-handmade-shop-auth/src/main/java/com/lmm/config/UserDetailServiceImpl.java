package com.lmm.config;

import cn.hutool.json.JSONUtil;
import com.lmm.entity.UserInfo;
import com.lmm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoService.lambdaQuery().eq(UserInfo::getPhone, phone).one();
        if (userInfo == null) {
            return null;
        }
        String password = userInfo.getPassword();
        String[] authorities = {"root"};
        userInfo.setPassword(null);
        return User.withUsername(JSONUtil.toJsonStr(userInfo)).password(password).authorities(authorities).build();
    }
}
