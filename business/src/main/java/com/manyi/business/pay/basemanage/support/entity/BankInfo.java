package com.manyi.business.pay.basemanage.support.entity;

import java.math.BigDecimal;

/**
 * @author ZhangYuFeng on 2015/6/17 0017,18:57.
 * @Description:
 * @reviewer:
 */
public class BankInfo {
    private Long bankInfoId;
    private String bankCode;
    private String bankName;
    private String bankTypeNo;
    private BigDecimal singleLimit;
    private BigDecimal singleDayLimit;

    public Long getBankInfoId() {
        return bankInfoId;
    }

    public void setBankInfoId(Long bankInfoId) {
        this.bankInfoId = bankInfoId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankTypeNo() {
        return bankTypeNo;
    }

    public void setBankTypeNo(String bankTypeNo) {
        this.bankTypeNo = bankTypeNo;
    }

    public BigDecimal getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(BigDecimal singleLimit) {
        this.singleLimit = singleLimit;
    }

    public BigDecimal getSingleDayLimit() {
        return singleDayLimit;
    }

    public void setSingleDayLimit(BigDecimal singleDayLimit) {
        this.singleDayLimit = singleDayLimit;
    }
}
