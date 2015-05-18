package com.manyi.business.etc.bean;

import com.manyi.business.order.bean.OrderItem;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/5/11.
 */
public class EtcInvoice extends OrderItem {


    private long etcInvoiceId;

    private String  invoiceHeader;

    private BigDecimal amount;

    private String postAddress;

    private String  phone;

    private String express;

    private String  courierNo;

    private String param;

    private long orderItemId;

    public long getEtcInvoiceId() {
        return etcInvoiceId;
    }

    public void setEtcInvoiceId(long etcInvoiceId) {
        this.etcInvoiceId = etcInvoiceId;
    }

    public String getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(String invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getCourierNo() {
        return courierNo;
    }

    public void setCourierNo(String courierNo) {
        this.courierNo = courierNo;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }
}
