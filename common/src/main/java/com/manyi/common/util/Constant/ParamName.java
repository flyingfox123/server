package com.manyi.common.util.Constant;

/**
 * @author ZhangYuFeng on 2015/6/23 0023,16:31.
 * @Description:
 * @reviewer:
 */
public enum ParamName {
    TRADESTATE("TradeState");
    private String paramName;
    ParamName(String paramName) {
        this.paramName=paramName;
    }
    public String getParamName(){
        return this.paramName;
    }
}
