package com.lmm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.entity.UserInfo;
import com.lmm.mapper.UserInfoMapper;
import com.lmm.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
