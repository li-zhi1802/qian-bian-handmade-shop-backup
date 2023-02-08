package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.RestResult;
import com.lmm.dto.UserRegisterFormDTO;
import com.lmm.entity.UserInfo;

public interface UserInfoService extends IService<UserInfo> {

    /**
     * 注册
     *
     * @param registerFormDTO
     * @return
     */
    RestResult register(UserRegisterFormDTO registerFormDTO);

    /**
     * 更新密码
     *
     * @param password
     * @param userId
     * @return
     */
    RestResult updatePassword(String password, Long userId);

    /**
     * 返回密码是否正确
     *
     * @param password
     * @param userId
     * @return
     */
    RestResult passwordIsRight(String password, Long userId);
}
