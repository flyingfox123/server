package com.manyi.common.bean.request.usercenter;

import com.manyi.common.bean.request.Head;
import com.manyi.common.bean.request.Parameter;

/**
 * Created by zhangyufeng on 2015/5/12 0012.
 */
public class LoginRequestBean {
    private Head head;
    private LoginDataContent dataContent;
    private Parameter parameter;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public LoginDataContent getDataContent() {
        return dataContent;
    }

    public void setDataContent(LoginDataContent dataContent) {
        this.dataContent = dataContent;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
