package com.manyi.business.pay.common.constant;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/10 0010
 */
public enum BusinessType {

    // 卡bin查询
    CARDBINQUERY("CardBinQuery"),
    // 实名认证
    UPAUTHENTICATE("UPAuthenticate"),
    // 实名修改
    UPCHANGEAUTH("UPChangeAuth"),
    // 开户行查询
    LBANKNOQUERY("LBankNoQuery"),
    // 运费结算
    LOGISTICSSETTLEMENT("LogisticsSettlement"),
    // 订单退款
    ORDERREFUND("OrderRefund"),
    ;

    private String businessType;

    BusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessType() {
        return this.businessType;
    }
}
