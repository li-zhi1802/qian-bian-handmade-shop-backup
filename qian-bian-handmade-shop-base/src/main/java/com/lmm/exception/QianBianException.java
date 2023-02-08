package com.lmm.exception;

import com.lmm.constant.SystemError;

/**
 * @author : 芝麻
 * @date : 2023-02-08 16:35
 **/
public class QianBianException extends RuntimeException {
    private final String errorMessage;

    public QianBianException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public QianBianException(SystemError systemError) {
        this.errorMessage = systemError.getErrorMessage();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
