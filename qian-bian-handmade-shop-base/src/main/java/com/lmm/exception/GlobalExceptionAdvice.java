package com.lmm.exception;

import com.lmm.dto.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 授权异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(AuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult catchDmException(AuthenticationServiceException e) {
        log.error("捕获  业务  异常：{}", e.getMessage());
        e.printStackTrace();
        return RestResult.fail(e.getMessage());
    }
}
