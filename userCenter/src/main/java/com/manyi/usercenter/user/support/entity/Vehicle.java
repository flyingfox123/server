package com.manyi.usercenter.user.support.entity;

import java.sql.Date;

/**
 * Created by zhangyufeng on 2015/5/27 0027.
 */
public class Vehicle {
    private Long id;
    private Long userId;
    private String plateNo;
    private String vehicleBrand;
    private double loadHeight;
    private String axlesNum;
    private String plateLength;
    private String plateType;
    private String luCard;
    private String oilWear;
    private Date createTime;
    private String loginName;
    private Long vehicleId;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public double getLoadHeight() {
        return loadHeight;
    }

    public void setLoadHeight(double loadHeight) {
        this.loadHeight = loadHeight;
    }

    public String getAxlesNum() {
        return axlesNum;
    }

    public void setAxlesNum(String axlesNum) {
        this.axlesNum = axlesNum;
    }

    public String getPlateLength() {
        return plateLength;
    }

    public void setPlateLength(String plateLength) {
        this.plateLength = plateLength;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public String getLuCard() {
        return luCard;
    }

    public void setLuCard(String luCard) {
        this.luCard = luCard;
    }

    public String getOilWear() {
        return oilWear;
    }

    public void setOilWear(String oilWear) {
        this.oilWear = oilWear;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
