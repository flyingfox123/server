package com.manyi.business.etc.support.dao;

import com.manyi.business.etc.bean.Etc;
import com.manyi.business.etc.bean.EtcInvoice;

/**
 * Created by Administrator on 2015/5/11.
 */
public interface EtcDao {

    /**
     * 增加etc记录
     * @param info
     */
    public void addETC(Etc info);

    /**
     * 增加etc发票记录
     * @param invoice
     */
    void addETCInvoice(EtcInvoice invoice);
}
