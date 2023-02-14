package com.lmm.handler;

import com.lmm.dto.RestResult;
import com.lmm.exception.QianBianException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(QianBianException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult catchQianBianException(QianBianException e) {
        log.error("捕获业务异常：{}", e.getErrorMessage());
        e.printStackTrace();
        return RestResult.fail(e.getErrorMessage());
    }

    /**
     * 捕获参数不合法异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult catchMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("捕获参数不合法异常：{}", e.getMessage());
        e.printStackTrace();
        StringBuilder errorResult = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> errorResult.append(error.getDefaultMessage()).append(","));
        return RestResult.fail(errorResult.toString());
    }

    /**
     * 捕获所有异常
     * 兜底方案
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult catchDException(Exception e) {
        log.error("捕获异常：{}", e.getMessage());
        e.printStackTrace();
        return RestResult.fail(e.getMessage());
    }

}
