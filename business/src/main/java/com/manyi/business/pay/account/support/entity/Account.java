package com.manyi.business.pay.account.support.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付账户实体类
 * author: WangPengfei
 */
public class Account implements Serializable {

    private long accountId;
    private long userId;
    private BigDecimal balance; // 余额
    private String realName;
    private String idCardNo;
    private Date createTime;
    private Date updateTime;
    private int type;
    private int State;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
