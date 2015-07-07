package com.manyi.business.pay.log.bean;

import java.sql.Date;

/**
 * Created by zhangyufeng on 2015/6/8 0008.
 */
public class BillInterLogBean {
    private Long billInterLogId;
    private Long billId;
    private String reqMessage;
    private Date reqTime;
    private String respMessage;
    private Date respTime;

    public Long getBillInterLogId() {
        return billInterLogId;
    }

    public void setBillInterLogId(Long billInterLogId) {
        this.billInterLogId = billInterLogId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getReqMessage() {
        return reqMessage;
    }

    public void setReqMessage(String reqMessage) {
        this.reqMessage = reqMessage;
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    public Date getRespTime() {
        return respTime;
    }

    public void setRespTime(Date respTime) {
        this.respTime = respTime;
    }
}
