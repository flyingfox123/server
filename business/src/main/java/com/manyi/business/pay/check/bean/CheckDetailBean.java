package com.manyi.business.pay.check.bean;

import java.sql.Date;

/**
 * @author ZhangYuFeng on 2015/6/25 0025,22:30.
 * @Description:
 * @reviewer:
 */
public class CheckDetailBean {
    private Long checkDetailId;
    private Long checkId;
    private String billNo;
    private String channelBillNo;
    private int state;
    private Date createTime;
    private int dealResult;

    private String createDate;

    private String createDateStart;
    private String createDateEnd;

    private String stateCn;

    public String getStateCn() {
        return stateCn;
    }

    public void setStateCn(String stateCn) {
        this.stateCn = stateCn;
    }

    public String getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(String createDateStart) {
        this.createDateStart = createDateStart;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
