package com.manyi.business.etc;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.etc.bean.ETCPreChargeRequest;
import com.manyi.business.etc.bean.Etc;
import com.manyi.business.etc.bean.EtcInvoice;
import com.manyi.business.etc.bean.EtcResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2015/5/11.
 */
public interface ETCService {

    /**
     * 批量创建ETC预充值信息
     * @param list
     */
     void createETCOrder(List<Etc> list) throws BusinessException;

    /**
     * 创建预充值发票信息
     * @param invoice
     */
     void  createETCInvoiceOrder(EtcInvoice invoice) throws BusinessException;


    /**
     * 预充值回调接口
     * @param request
     */
     void preChargeETCCallBack(ETCPreChargeRequest request) throws BusinessException;

     void  EtcInvoiceCallBack(EtcResult callBack) throws BusinessException;




}
