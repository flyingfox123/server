package com.manyi.business.pay.bill.bean;


import com.manyi.base.entity.Pagination;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-5
 * @reviewer
 */
public class BillBean extends Pagination {
    private long billId;
    private String billNo;
    private String orderNo;
    private long orderId;
    private long accountId;
    private int sourceId=1;
    private int channelId;
    private int tradeType;
    private int businessType;
    private Date tradeTime;
    private String channelBillNo;
    private int tradeState;
    private int checkState;
    private BigDecimal amount;
    private long userId;

    private BigDecimal minAmount;
    private BigDecimal maxAmount;

    private String tradeTimeStart;
    private String tradeTimeEnd;

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getTradeTimeStart() {
        return tradeTimeStart;
    }

    public void setTradeTimeStart(String tradeTimeStart) {
        this.tradeTimeStart = tradeTimeStart;
    }

    public String getTradeTimeEnd() {
        return tradeTimeEnd;
    }

    public void setTradeTimeEnd(String tradeTimeEnd) {
        this.tradeTimeEnd = tradeTimeEnd;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
}
