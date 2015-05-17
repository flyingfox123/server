package com.manyi.common.bean.request.usercenter.user;

import com.manyi.common.bean.request.Head;
import com.manyi.common.bean.request.Parameter;

/**
 * Created by zhangyufeng on 2015/5/12 0012.
 */
public class MsgCodeRequestBean {
    private Head head;
    private Parameter parameter;
    private MsgCodeContent dataContent;

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

    public MsgCodeContent getDataContent() {
        return dataContent;
    }

    public void setDataContent(MsgCodeContent dataContent) {
        this.dataContent = dataContent;
    }
}
