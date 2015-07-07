package com.manyi.business.pay.check.bean;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author ZhangYuFeng on 2015/6/11 0011,18:25.
 * @Description:
 * @reviewer:
 */
public class CheckChannelBean {
    private String billNo;
    private int payWay;
    private String billState;
    private BigDecimal amount;
    private BigDecimal fee;
    private String exchangeNo;
    private int payType;
    private Long channelId;
    private String orderTime;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billId) {
        this.billNo = billId;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getExchangeNo() {
        return exchangeNo;
    }

    public void setExchangeNo(String exchangeNo) {
        this.exchangeNo = exchangeNo;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
