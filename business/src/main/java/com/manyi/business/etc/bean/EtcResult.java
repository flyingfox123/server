package com.manyi.business.etc.bean;

/**
 * Created by Administrator on 2015/5/14.
 */
public class EtcResult {

    private String orderItemSeq ;

    private Integer state;

    private String invoiceNo;

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOrderItemSeq() {
        return orderItemSeq;
    }

    public void setOrderItemSeq(String orderItemSeq) {
        this.orderItemSeq = orderItemSeq;
    }
}
