package com.manyi.usercenter.user.support.entity;

/**
 * Created by zhangyufeng on 2015/6/4 0004.
 */
public class UserVehicle {
    private String loginName;
    private String name;
    private String plateNo;
    private String luCard;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getLuCard() {
        return luCard;
    }

    public void setLuCard(String luCard) {
        this.luCard = luCard;
    }
}
