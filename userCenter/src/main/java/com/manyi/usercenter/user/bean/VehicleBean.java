package com.manyi.usercenter.user.bean;

/**
 * Created by zhangyufeng on 2015/5/27 0027.
 */
public class VehicleBean {
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
}
