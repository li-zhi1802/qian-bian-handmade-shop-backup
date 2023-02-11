package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.UserRegisterFormDTO;
import com.lmm.entity.UserInfo;
import com.lmm.vo.UserVO;

public interface UserInfoService extends IService<UserInfo> {

    /**
     * 注册
     *
     * @param registerFormDTO
     * @return
     */
    Boolean register(UserRegisterFormDTO registerFormDTO);

    /**
     * 更新密码
     *
     * @param password
     * @param userId
     * @return
     */
    Boolean updatePassword(String password, Long userId);

    /**
     * 返回密码是否正确
     *
     * @param password
     * @param userId
     * @return
     */
    Boolean passwordIsRight(String password, Long userId);

    UserVO getDetailUserInfo(Long userId);
}
