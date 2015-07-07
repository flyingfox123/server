package com.manyi.business.pay.basemanage.bean;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-17
 * @reviewer
 */
public class BankInfoBean {
    private Long bankInfoId;
    private String bankCode;
    private String bankName;
    private String bankNo;

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

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
}
