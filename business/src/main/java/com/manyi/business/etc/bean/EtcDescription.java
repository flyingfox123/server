package com.manyi.business.etc.bean;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class EtcDescription {

    private long id ;

    private String ETCCode;

    private String plateNum;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
