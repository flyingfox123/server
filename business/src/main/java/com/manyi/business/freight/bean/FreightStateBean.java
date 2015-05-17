package com.manyi.business.freight.bean;

import java.sql.Date;

/**
 * Created by zhangyufeng on 2015/4/13 0013.
 */
public class FreightStateBean {
    private long id;
    private long freightChargeID;
    private String state;
    private Date createTime;
    private String changeStateReason;
    private String changeStateReply;
    private String submintPerson;
    private String replyPerson;
    private Date replyDate;
    private String freightFlowNo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFreightChargeID() {
        return freightChargeID;
    }

    public void setFreightChargeID(long freightChargeID) {
        this.freightChargeID = freightChargeID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChangeStateReason() {
        return changeStateReason;
    }

    public void setChangeStateReason(String changeStateReason) {
        this.changeStateReason = changeStateReason;
    }

    public String getChangeStateReply() {
        return changeStateReply;
    }

    public void setChangeStateReply(String changeStateReply) {
        this.changeStateReply = changeStateReply;
    }

    public String getSubmintPerson() {
        return submintPerson;
    }

    public void setSubmintPerson(String submintPerson) {
        this.submintPerson = submintPerson;
    }

    public String getReplyPerson() {
        return replyPerson;
    }

    public void setReplyPerson(String replyPerson) {
        this.replyPerson = replyPerson;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public String getFreightFlowNo() {
        return freightFlowNo;
    }

    public void setFreightFlowNo(String freightFlowNo) {
        this.freightFlowNo = freightFlowNo;
    }
}
