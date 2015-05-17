package com.manyi.rating.entity.common;

/**
 * Created by Administrator on 2015/3/18.
 */
public class RootMsg {
    /*
    错误信息
     */
    private String errorMsg;

    /*
    返回码
    */
    private Integer codeMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(Integer codeMsg) {
        this.codeMsg = codeMsg;
    }
}
