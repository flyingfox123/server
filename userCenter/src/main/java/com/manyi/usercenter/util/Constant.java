package com.manyi.usercenter.util;

/**
 * Created by zhangyufeng on 2015/5/13 0013.
 */
public enum Constant {
    TOKEN("token"),
    LOGINNAME("loginName");

    private String value;

    public String getValue() {
        return value;
    }

    Constant(String value){
        this.value=value;
    }
}
