package com.manyi.business.etc.bean;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class EtcOrderAmountResult {

    private String ETCCode;

    private String plateNum;

    private double monthAmount;

    private double yearAmount;

    private String driverName;

    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getETCCode() {
        return ETCCode;
    }

    public void setETCCode(String ETCCode) {
        this.ETCCode = ETCCode;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public double getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(double monthAmount) {
        this.monthAmount = monthAmount;
    }

    public double getYearAmount() {
        return yearAmount;
    }

    public void setYearAmount(double yearAmount) {
        this.yearAmount = yearAmount;
    }
}
