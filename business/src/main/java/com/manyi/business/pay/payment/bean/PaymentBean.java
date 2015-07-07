package com.manyi.business.pay.payment.bean;

import java.math.BigDecimal;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/11 0011
 */
public class PaymentBean {

    private String orderNo;
    private String billNo;
    private long userId;
    private BigDecimal amount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
