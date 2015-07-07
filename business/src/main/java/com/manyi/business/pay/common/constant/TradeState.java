package com.manyi.business.pay.common.constant;

import com.manyi.base.entity.*;
import com.manyi.base.entity.Number;

/**
 * @author ZhangYuFeng on 2015/6/18 0018,14:41.
 * @Description:
 * @reviewer:
 */
public enum TradeState {
    PENDING(Number.NUM_1),//待处理
    PAYSUCCESS(Number.NUM_2),// 支付成功
    PAYFAIL(Number.NUM_3),// 支付失败
    ACCEPTSUCCESS(Number.NUM_4),// 受理成功
    ACCEPTFAIL(Number.NUM_5),// 受理失败
    REFUNDPENDING(Number.NUM_6),// 退款待审核
    ACCEPTTIMEOUT(Number.NUM_7);// 受理超时
    private int tradeState;
    TradeState(int i){
        this.tradeState=i;
    }

    public int getTradeState(){
        return this.tradeState;
    }
}
