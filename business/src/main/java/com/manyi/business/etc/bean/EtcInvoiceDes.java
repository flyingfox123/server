package com.manyi.business.etc.bean;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/5/17.
 */
public class EtcInvoiceDes {

    private String  invoiceHeader;

    private BigDecimal amount;

    private String postAddress;

    private String  phone;

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
}
