package com.manyi.business.pay.settlement.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运费结算实体
 * Author：WangPengfei
 * Review：
 * Date：2015/6/15 0015
 */
public class FeeSettlementBean implements Serializable{

    private long userId;
    private BigDecimal logisticsAmt; // 付款金额
    private String userName;
    private String realName;
    private String idCardNo;
    private String mobile;
    private String cardNo;
    private String bankName;
    private String bankBranch;
    private String provNo; // 收款方省份编码
    private String billNo;
    private String lBankNo; // 联行号
    private String crBankType; // 行别代码

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getLogisticsAmt() {
        return logisticsAmt;
    }

    public void setLogisticsAmt(BigDecimal logisticsAmt) {
        this.logisticsAmt = logisticsAmt;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
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

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getProvNo() {
        return provNo;
    }

    public void setProvNo(String provNo) {
        this.provNo = provNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getLBankNo() {
        return lBankNo;
    }

    public void setLBankNo(String lBankNo) {
        this.lBankNo = lBankNo;
    }

    public String getCrBankType() {
        return crBankType;
    }

    public void setCrBankType(String crBankType) {
        this.crBankType = crBankType;
    }
}
