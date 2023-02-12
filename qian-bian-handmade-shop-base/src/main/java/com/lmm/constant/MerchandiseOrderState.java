package com.lmm.constant;

/**
 * @author : 芝麻
 * @date : 2023-02-11 20:39
 **/
public enum MerchandiseOrderState {
    /**
     * 订单取消
     */
    ORDER_CANCELED("001001", "订单取消"),
    /**
     * 待支付
     */
    TO_BE_PAID("001002", "待支付"),
    /**
     * 支付中
     */
    PAYING("001003", "支付中"),
    /**
     * 待发货
     */
    TO_BE_SHIPPED("001004", "待发货"),
    /**
     * 待收货
     */
    TO_BE_RECEIVED("001005", "待收货"),
    /**
     * 待评价
     */
    TO_BE_COMMENT("001006", "待评价"),
    /**
     * 订单完成
     */
    ORDER_COMPLETION("001007", "订单完成"),
    /**
     * 售后中
     */
    AFTER_SALES("001008", "售后中"),
    /**
     * 退款成功
     */
    REFUND_SUCCESSFUL("001009", "退款成功");
    private String code;
    private String desc;

    MerchandiseOrderState(String code, String desc) {
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
