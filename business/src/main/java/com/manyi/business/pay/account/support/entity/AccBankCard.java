package com.manyi.business.pay.account.support.entity;

import java.io.Serializable;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/2
 */
public class AccBankCard implements Serializable {

    private long accCardId;
    private long accountId;
    private String mobile;
    private String cardNo;
    private String bankName;
    private String bankCode;
    private String bankBranch;
    private String lBankNo;
    private String provNo;
    private String crBankType;
    private int type;
    private int usage;
    private int bindState;

    public long getAccCardId() {
        return accCardId;
    }

    public void setAccCardId(long accCardId) {
        this.accCardId = accCardId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getlBankNo() {
        return lBankNo;
    }

    public void setlBankNo(String lBankNo) {
        this.lBankNo = lBankNo;
    }

    public String getProvNo() {
        return provNo;
    }

    public void setProvNo(String provNo) {
        this.provNo = provNo;
    }

    public String getCrBankType() {
        return crBankType;
    }

    public void setCrBankType(String crBankType) {
        this.crBankType = crBankType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public int getBindState() {
        return bindState;
    }

    public void setBindState(int bindState) {
        this.bindState = bindState;
    }
}
