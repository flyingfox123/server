package com.manyi.business.pay.common.constant;

/**
 * @author ZhangYuFeng on 2015/6/10 0010,16:11.
 * @Description:
 * @reviewer:
 */
public enum ParamValue {
    MD5("MD5"),
    CHECKORDER("CheckOrder"),
    VERSION_NO_1("1.0");
    private String paramValue;
    ParamValue(String paramValue){
        this.paramValue=paramValue;
    }
    public String getParamValue(){
        return this.paramValue;
    }
}
