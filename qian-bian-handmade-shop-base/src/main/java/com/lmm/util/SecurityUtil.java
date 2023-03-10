package com.lmm.util;

import com.alibaba.fastjson.JSON;
import com.lmm.dto.UserDTO;
import com.lmm.exception.QianBianException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.lmm.constant.SystemError.TOKEN_PARSE_ERROR;

@Slf4j
public class SecurityUtil {
    public static UserDTO getUser() {
        Object principalObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //取出用户身份信息
        String principalJsonStr = principalObj.toString();
        try {
            //将json转成对象
            return JSON.parseObject(principalJsonStr, UserDTO.class);
        } catch (Exception e) {
            log.error("token的payload解析失败，json：{}", principalJsonStr);
            e.printStackTrace();
            throw new QianBianException(TOKEN_PARSE_ERROR);
        }
    }
}
