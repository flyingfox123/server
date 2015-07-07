package com.manyi.business.pay.common.constant;

/**
 * @author ZhangYuFeng on 2015/6/10 0010,15:10.
 * @Description:
 * @reviewer:
 */
public enum ParamKey {

    //******************************** 信联接口公共参数 ******************************//

    CHAR_SET("char_set"),
    PARTNER_ID("partner_id"),
    REQUEST_ID("request_id"),
    SIGN_TYPE("sign_type"),
    BIZ_TYPE("biz_type"),
    VERSION_NO("version_no"),
    CHECK_DT("check_dt"),
    MAC("mac"),
    DOWNLOAD_URL("download_url"),
    ORDER_ID("order_id"),
    // 银行卡号(卡bin查询、实名认证)
    CARD_NO("card_no"),
    // 签约绑定对象(实名认证接口、运费结算接口)
    SIGN_OBJ_ID("sign_obj_id"),
    // 银行编码(卡bin接口、开户行查询接口)
    BANK_CODE("bank_code"),
    // 返回码(卡bin查询、开户行查询、支付订单查询、对账)
    MESSAGE_CODE("message_code"),

    //******************************** 卡bin查询参数 ******************************//

    // 银行名称
    BANK_NAME("bank_name"),

    //******************************** 实名认证参数 ******************************//

    BUSI_ID("busi_id"),
    CUSTOMER_NAME("customer_name"),
    CERT_TYPE("cert_type"),
    CERT_NO("cert_no"),
    CARD_TYPE("card_type"),
    CARD_TEL("card_tel"),
    SIGN_RESULT("sign_result"),

    //******************************** 支付回调参数 ******************************//
    PAY_NO("pay_no"),
    TOTAL_AMOUNT("total_amount"),
    PAY_DATE("pay_date"),
    PAY_STATUS("status"),
    ORDER_DATE("order_date"),
    PAY_FEE("fee"),

    //******************************** 开户行查询参数 ******************************//
    // 联行号
    LBANK_NO("lBank_no"),
    // 行别类型
    BANK_TYPE("bank_type"),
    // 省份编号
    PROVINCE_CODE("province_code"),
    // 城市编码
    CITY_CODE("city_code"),

    //******************************** 运费结算请求参数 ******************************//
    // 收款方户名
    CRACCNAME("crAccName"),
    // 收款方开户行名
    CRBANKNAME("crBankName"),
    // 收款方开户行号
    CRBANKNO("crBankNo"),
    // 行别代码
    CRBANKTYPE("crBankType"),
    // 收款方省份编码
    CRPROV("crProv"),
    // 收款方账号
    CRACCNO("crAccNo"),
    // 付款金额
    LOGISTICSAMT("logisticsAmt"),
    // 付款申请流水号
    REQSEQNO("reqSeqNo"),
    // 付款响应流水号
    RESSEQNO("resSeqNo"),
    // 交易时间
    SETTLE_TIME("settle_time"),
    // 结算结果
    SETTLE_RESULT("settle_result"),

    //******************************** 退款请求参数 ******************************//
    // 退款金额(以分为单位)
    REFUND_AMOUNT("refund_amount"),
    // 退款结果
    STATUS("status"),

    ;
    private String paramKey;
    ParamKey(String paramKey){
        this.paramKey=paramKey;
    }
    public String getParamKey(){
        return this.paramKey;
    }
}
