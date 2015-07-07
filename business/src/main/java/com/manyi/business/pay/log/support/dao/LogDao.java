package com.manyi.business.pay.log.support.dao;

import com.manyi.business.pay.log.bean.BillInterLogBean;

public interface LogDao {

    /**
     * 保存请求报文
     * @param billInterLogBean
     */
    void addRequestStr(BillInterLogBean billInterLogBean);

    /**
     * 保存返回报文
     * @param billInterLogBean
     */
    void addResponseStr(BillInterLogBean billInterLogBean);
}