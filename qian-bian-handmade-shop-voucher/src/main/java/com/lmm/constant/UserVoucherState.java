package com.lmm.constant;

public enum UserVoucherState {
    /**
     * 已领取
     */
    RECEIVED("003001", "未使用"),
    /**
     * 已过期
     */
    EXPIRED("003002", "已过期"),
    /**
     * 已使用
     */
    USED("003002", "已使用"),
    ;
    private String code;
    private String desc;

    UserVoucherState(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}