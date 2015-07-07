package com.manyi.business.pay.bill.support.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by zhangyufeng on 2015/6/5 0005.
 */
public class Bill {
    private long billId;
    private String billNo;
    private String orderNo;
    private long orderId;
    private long accountId;
    private int sourceId;
    private int channelId;
    private Date createTime;
    private BigDecimal amount;
    private int tradeType;
    private int businessType;
    private Date tradeTime;
    private String channelBillNo;
    private int tradeState;
    private int checkState;
    private Date responseTime;

    private String tradeTypeCn;
    private String checkStateCn;
    private String tradeStateCn;
    private String sourceIdCn;

    public String getSourceIdCn() {
        return sourceIdCn;
    }

    public void setSourceIdCn(String sourceIdCn) {
        this.sourceIdCn = sourceIdCn;
    }

    public String getTradeTypeCn() {
        return tradeTypeCn;
    }

    public void setTradeTypeCn(String tradeTypeCn) {
        this.tradeTypeCn = tradeTypeCn;
    }

    public String getCheckStateCn() {
        return checkStateCn;
    }

    public void setCheckStateCn(String checkStateCn) {
        this.checkStateCn = checkStateCn;
    }

    public String getTradeStateCn() {
        return tradeStateCn;
    }

    public void setTradeStateCn(String tradeStateCn) {
        this.tradeStateCn = tradeStateCn;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getChannelBillNo() {
        return channelBillNo;
    }

    public void setChannelBillNo(String channelBillNo) {
        this.channelBillNo = channelBillNo;
    }

    public int getTradeState() {
        return tradeState;
    }

    public void setTradeState(int tradeState) {
        this.tradeState = tradeState;
    }

    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }
}
