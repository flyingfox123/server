package com.manyi.business.pay.common.constant;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/10 0010
 */
public enum CardType {

    // 借记卡(储蓄卡)
    SAVINGSCARD("0"),
    // 信用卡
    CREDITCARD("1");

    private String cardType;

    CardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return this.cardType;
    }
}
