package com.manyi.business.pay.account.bean;

import com.manyi.base.entity.Page;
import com.manyi.base.entity.Pagination;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户业务接收层
 * Author：WangPengfei
 * Review：
 * Date：2015/6/2
 */
public class AccountInfoBean extends Page implements Serializable {

    private long accountId;
    private String userName;
    private long userId;
    private BigDecimal balance;
    private String realName;
    private String idCardNo;
    private String mobile;
    private String cardNo;
    private String bankName;
    private String bankCode;
    private String bankBranch;
    private String provNo;
    private String lBankNo;
    private String crBankType;
    private Date createTime;
    private Date updateTime;
    private int accType;
    private int state;
    private int cardType;
    private int usage;
    private int bindState;
    private String verifyCode;

    private String loginName;
    private String startTime;
    private String endTime;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public String getProvNo() {
        return provNo;
    }

    public void setProvNo(String provNo) {
        this.provNo = provNo;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getAccType() {
        return accType;
    }

    public void setAccType(int accType) {
        this.accType = accType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        state = state;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
