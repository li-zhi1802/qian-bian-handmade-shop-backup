package com.lmm.constant;

public enum SystemError {
    /**
     * 非主动抛出的错误
     */
    UN_KNOWN_ERROR("服务器异常"),
    /**
     * token解析失败
     */
    TOKEN_PARSE_ERROR("Token解析失败"),
    ;
    private final String errorMessage;

    SystemError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}