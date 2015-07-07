package com.manyi.usercenter.util;

/**
 * Created by zhangyufeng on 2015/5/13 0013.
 */
public enum Constant {
    TOKEN("token"),
    LOGINNAME("loginName"),
    REGESTERUSER("regesterUser"),
    RESETPASS("resetPass"),
    REGISTERCODE("registerCode"),
    FINDPASSWORD("findPassword"),
    INDIVIDUAL("13"),
    CORPORATION("14"),
    PICDIR("opt/tomcat/data/pictures"),
    PICTURES("pictures"),
    HEADPICDIR("headPic");

    private String value;

    public String getValue() {
        return value;
    }

    Constant(String value){
        this.value=value;
    }
}
