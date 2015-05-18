package com.manyi.business.order;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.order.bean.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liukaihua on 2015/5/4.
 */
public interface OrderService {

    /**
     * 下单
     * @param order
     */
    Order subscribe(Order order) throws BusinessException;

    /**
     * 取消订单
     * @param orderSeqNo
     */
    void cancelOrder(String orderSeqNo) throws BusinessException;

    /**
     * 取消订单项
     * @param itemSeqNo
     */
    void cancelOrderItem(String itemSeqNo) throws BusinessException;

    /**
     * 支付订单
     * @param orderSeqNo
     * @param amount
     */
    void payForOrder(String orderSeqNo , BigDecimal amount , int payChannel) throws BusinessException;

    /**
     * 订单申请退款
     * @param orderSeqNo
     */
    void drawbackOrder(String orderSeqNo) throws BusinessException;

    /**
     * 条件查询订单
     * @param order
     * @return
     */
    QueryOrderResult queryOrder(QueryOrderCondition order);

    /**
     * 条件查询订单项
     * @param condition
     * @return
     */
    QueryOrderItemResult queryOrderItem(QueryOrderItemCondition condition);

    /**
     * 订单支付回调接口
     */
    void orderPayCallBack(String orderSeqNo, BigDecimal amount , int payChannel) throws BusinessException;

}
