package com.manyi.business.etc;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.etc.bean.*;
import com.manyi.business.order.bean.Order;
import com.manyi.business.order.bean.OrderItem;
import com.manyi.business.order.bean.QueryOrderItemCondition;

import java.math.BigDecimal;
import java.util.List;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public interface ETCService {

    /**
     * 批量创建ETC预充值信息
     * @param etc
     */
     OrderItem createETCOrder(Etc etc) throws BusinessException;

    /**
     * 创建预充值发票信息
     * @param invoice
     */
     OrderItem  createETCInvoiceOrder(EtcInvoice invoice) throws BusinessException;


    /**
     * 预充值回调接口
     * @param request
     */
     void preChargeETCCallBack(ETCPreChargeRequest request) throws BusinessException, APIConnectionException, APIRequestException;

    /**
     * 发票处理回调接口
     * @param callBack
     * @throws BusinessException
     */
     void  EtcInvoiceCallBack(EtcResult callBack) throws BusinessException;

    /**
     * 查询用户充值历史记录（按充值次数排序）
     * @param userId
     * @return
     */
    List<EtcDescription> queryETCChargeHistory(long userId );


    /**
     * 查询当前，月、年的充值数据
     * @param userId
     * @return
     */
    EtcOrderAmountResult queryEtcOrderAmount(long userId) throws BusinessException;



    /**
     * 订单支付回调接口
     */
    void etcPayCallBack(String orderSeqNo, String amount , int payChannel ,int state) throws BusinessException;

      /*
      查询ETC子订单详情
      */
    List<Etc> queryETCItems(QueryOrderItemCondition condition);
}
