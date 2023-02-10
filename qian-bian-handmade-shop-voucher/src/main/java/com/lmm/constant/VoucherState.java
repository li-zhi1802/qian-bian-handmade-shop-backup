package com.lmm.constant;

/**
 * @author : 芝麻
 * @date : 2023-02-10 11:34
 **/
public enum VoucherState {
    /**
     * 暂未发放
     */
    NOT_YET_ISSUED("002001", "暂未发放"),
    /**
     * 已发放
     */
    ISSUED("002002", "已发放"),
    /**
     * 已下架
     */
    HAS_TAKEN_OFF("002003", "已下架"),
    /**
     * 已过期
     */
    EXPIRED("002004", "已过期");
    private String code;
    private String desc;

    VoucherState(String code, String desc) {
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