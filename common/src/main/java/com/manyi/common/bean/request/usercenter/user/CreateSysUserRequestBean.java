package com.manyi.common.bean.request.usercenter.user;

import com.manyi.common.bean.request.Head;
import com.manyi.common.bean.request.Parameter;

/**
 * Created by zhangyufeng on 2015/5/12 0012.
 */
public class CreateSysUserRequestBean {
    private Head head;
    private Parameter parameter;
    private CreateSysUserContent dataContent;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public CreateSysUserContent getDataContent() {
        return dataContent;
    }

    public void setDataContent(CreateSysUserContent dataContent) {
        this.dataContent = dataContent;
    }
}
