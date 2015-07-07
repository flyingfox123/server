package com.manyi.business.pay.log;

import com.manyi.business.pay.log.bean.BillInterLogBean;

/**
 * Created by zhangyufeng on 2015/6/9 0009.
 */
public interface LogService {

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
