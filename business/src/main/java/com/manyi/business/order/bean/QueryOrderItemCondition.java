package com.manyi.business.order.bean;

import com.manyi.base.entity.Pagination;

/**
 * Created by Administrator on 2015/5/5.
 */
public class QueryOrderItemCondition extends Pagination {

    private long  orderId;

    private long userId;

    private  String state;

    private String type;

    private  String seqNo;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
}
