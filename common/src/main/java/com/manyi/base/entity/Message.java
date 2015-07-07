package com.manyi.base.entity;

import java.lang.Object;

/**
 * @Description: 消息类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class Message
{
    /*
    错误信息
     */
    private String errorMsg;

    /*
    返回码
    */
    private Integer codeMsg;


    private Object  response;


    public void setCodeMsg(String errorCode) {
        if (errorCode != null){
            this.codeMsg = Integer.valueOf(errorCode);
        }

    }

    public Integer getCodeMsg() {
        return codeMsg;
    }


    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }



    public void setCodeMsg(Integer codeMsg) {
        this.codeMsg = codeMsg;
    }
}
