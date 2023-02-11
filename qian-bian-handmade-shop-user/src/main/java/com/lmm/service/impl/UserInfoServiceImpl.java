package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.dto.UserRegisterFormDTO;
import com.lmm.entity.DeliveryAddress;
import com.lmm.entity.UserInfo;
import com.lmm.mapper.UserInfoMapper;
import com.lmm.service.DeliveryAddressService;
import com.lmm.service.UserInfoService;
import com.lmm.vo.DeliveryAddressVO;
import com.lmm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @Override
    public Boolean register(UserRegisterFormDTO registerFormDTO) {
        UserInfo registerUserInfo = BeanUtil.copyProperties(registerFormDTO, UserInfo.class);
        registerUserInfo.setPassword(passwordEncoder.encode(registerUserInfo.getPassword()));
        return save(registerUserInfo);
    }

    @Override
    public Boolean updatePassword(String password, Long userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setPassword(passwordEncoder.encode(password));
        return updateById(userInfo);
    }

    @Override
    public Boolean passwordIsRight(String password, Long userId) {
        String rightPassword = lambdaQuery().eq(UserInfo::getId, userId).select(UserInfo::getPassword).one().getPassword();
        // 判断是否正确
        return passwordEncoder.matches(password, rightPassword);
    }

    @Override
    public UserVO getDetailUserInfo(Long userId) {
        UserInfo userInfo = getById(userId);
        UserVO userVO = BeanUtil.copyProperties(userInfo, UserVO.class);
        if (userInfo.getDefaultAddressId() != null) {
            DeliveryAddress deliveryAddress = deliveryAddressService.getById(userInfo.getDefaultAddressId());
            userVO.setDefaultDeliveryAddress(BeanUtil.copyProperties(deliveryAddress, DeliveryAddressVO.class));
        }
        return userVO;
    }
}
