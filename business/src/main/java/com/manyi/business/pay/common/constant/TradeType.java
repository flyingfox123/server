package com.manyi.business.pay.common.constant;

import com.manyi.base.entity.*;
import com.manyi.base.entity.Number;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/11 0011
 */
public enum TradeType {

    // 付款
    PAYMENT(Number.NUM_1),
    // 收款
    RECEIVABLE(Number.NUM_2),
    // 退款
    REFUND(Number.NUM_3),
    ;
    private int tradeType;

    TradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public int getTradeType() {
        return this.tradeType;
    }
}
