package com.manyi.business.highwaytoll.bean;

/**
 * @Description: 通行费Bean
 * @author zhaoyuxin
 * @version: 1.0.0 2015-06-23.
 * @reviewer:
 */
public class HighwayTollBean {

    private String beginX;

    private String beginY;

    private String EndX;

    private String EndY;

    private int axisNum;

    private double oilWear;

    private double unitPrice;

    private double totalWeight;

    public String getBeginX() {
        return beginX;
    }

    public void setBeginX(String beginX) {
        this.beginX = beginX;
    }

    public String getBeginY() {
        return beginY;
    }

    public void setBeginY(String beginY) {
        this.beginY = beginY;
    }

    public String getEndX() {
        return EndX;
    }

    public void setEndX(String endX) {
        EndX = endX;
    }

    public String getEndY() {
        return EndY;
    }

    public void setEndY(String endY) {
        EndY = endY;
    }

    public int getAxisNum() {
        return axisNum;
    }

    public void setAxisNum(int axisNum) {
        this.axisNum = axisNum;
    }

    public double getOilWear() {
        return oilWear;
    }

    public void setOilWear(double oilWear) {
        this.oilWear = oilWear;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

}
