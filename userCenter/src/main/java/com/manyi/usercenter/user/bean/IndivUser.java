package com.manyi.usercenter.user.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Magic on 2015/4/16.
 */
public class IndivUser implements Serializable {

    private String loginName;
    private String password;
    private String secretKey;
    private String type;
    private String State;
    private Date createTime;
    private long userId;
    private Date updateTime;
    private String plateNo;
    private String plateType;
    private Double plateLength;
    private Double plateLoad;
    private String expectDestination;
    private String driverName;
    private String idCardNo;
    private String owner;
    private String ownerMobile;
    private String description;
    private String plateSerialNo;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public Double getPlateLength() {
        return plateLength;
    }

    public void setPlateLength(Double plateLength) {
        this.plateLength = plateLength;
    }

    public Double getPlateLoad() {
        return plateLoad;
    }

    public void setPlateLoad(Double plateLoad) {
        this.plateLoad = plateLoad;
    }

    public String getExpectDestination() {
        return expectDestination;
    }

    public void setExpectDestination(String expectDestination) {
        this.expectDestination = expectDestination;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlateSerialNo() {
        return plateSerialNo;
    }

    public void setPlateSerialNo(String plateSerialNo) {
        this.plateSerialNo = plateSerialNo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
