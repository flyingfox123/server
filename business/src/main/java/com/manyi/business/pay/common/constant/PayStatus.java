package com.manyi.business.pay.common.constant;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/10 0010
 */
public enum PayStatus {

    //************************************** 交易接口中的交易状态 *****************************************//
    // 预登记
    RECEIVED("BA"),
    // 等待付款（未选择支付渠道）
    WAIT_TO_PAY_UNCHOICECHANNEL("BB"),
    // 等待付款（已选支付渠道）
    WAIT_TO_PAY_CHOICECHANNEL("BC"),
    // 付款成功
    TRADE_SUCCESS("BD"),
    // 交易关闭
    TRADE_CLOSE("BE"),
    // 交易过期
    TRADE_OVERDUE("BF"),


    //************************************** 结算接口中的交易状态 *****************************************//
    // 银行受理付款请求
    SETTLE_RECEIVED("8888"),
    // 结算成功
    SETTLE_SUCCESS("0000"),
    // 结算失败
    SETTLE_FAILED("9999"),
    // 结算失败，银行退款
    SETTLE_REFUND("7777"),

    //************************************** 退款接口中的交易状态 *****************************************//
    // 退款成功
    REFUND_SUCCESS("SUCCESS"),
    // 退款失败
    REFUND_FAILED("FAILED"),
    ;

    private String payStatusCode;

    PayStatus(String payStatusCode) {
        this.payStatusCode = payStatusCode;
    }

    public String getPayStatusCode() {
        return this.payStatusCode;
    }

}
