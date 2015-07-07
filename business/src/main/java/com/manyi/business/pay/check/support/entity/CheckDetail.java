package com.manyi.business.pay.check.support.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author ZhangYuFeng on 2015/6/16 0016,9:17.
 * @Description:
 * @reviewer:
 */
public class CheckDetail {
    private Long checkDetailId;
    private Long checkId;
    private String billNo;
    private String channelBillNo;
    private int state;
    private Date createTime;
    private int dealResult;

    private int count;
    private String createDate;
    private String stateCn;

    private BigDecimal BillAmount;
    private BigDecimal ChannelAmount;

    public BigDecimal getBillAmount() {
        return BillAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        BillAmount = billAmount;
    }

    public BigDecimal getChannelAmount() {
        return ChannelAmount;
    }

    public void setChannelAmount(BigDecimal channelAmount) {
        ChannelAmount = channelAmount;
    }

    public String getStateCn() {
        return stateCn;
    }

    public void setStateCn(String stateCn) {
        this.stateCn = stateCn;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getCheckDetailId() {
        return checkDetailId;
    }

    public void setCheckDetailId(Long checkDetailId) {
        this.checkDetailId = checkDetailId;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getChannelBillNo() {
        return channelBillNo;
    }

    public void setChannelBillNo(String channelBillNo) {
        this.channelBillNo = channelBillNo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getDealResult() {
        return dealResult;
    }

    public void setDealResult(int dealResult) {
        this.dealResult = dealResult;
    }
}
