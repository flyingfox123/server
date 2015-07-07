package com.manyi.business.highwaytoll.bean;

import java.util.List;

/**
 * @Description: 通行费Bean
 * @author zhaoyuxin
 * @version: 1.0.0 2015-06-16.
 * @reviewer:
 */
public class HighwayResponseBean {

    private String totalFee;

    private String oilFee;

    private String highwayFee;

    private String distance;

    private List chargePoints;

    private int bridgeNum;

    private int tunnelNum;

    private int provinceNum;

    private int highwayNum;

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getOilFee() {
        return oilFee;
    }

    public void setOilFee(String oilFee) {
        this.oilFee = oilFee;
    }

    public List getChargePoints() {
        return chargePoints;
    }

    public void setChargePoints(List chargePoints) {
        this.chargePoints = chargePoints;
    }

    public int getBridgeNum() {
        return bridgeNum;
    }

    public void setBridgeNum(int bridgeNum) {
        this.bridgeNum = bridgeNum;
    }

    public int getTunnelNum() {
        return tunnelNum;
    }

    public void setTunnelNum(int tunnelNum) {
        this.tunnelNum = tunnelNum;
    }

    public int getProvinceNum() {
        return provinceNum;
    }

    public void setProvinceNum(int provinceNum) {
        this.provinceNum = provinceNum;
    }

    public int getHighwayNum() {
        return highwayNum;
    }

    public void setHighwayNum(int highwayNum) {
        this.highwayNum = highwayNum;
    }

    public String getHighwayFee() {
        return highwayFee;
    }

    public void setHighwayFee(String highwayFee) {
        this.highwayFee = highwayFee;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }




}
