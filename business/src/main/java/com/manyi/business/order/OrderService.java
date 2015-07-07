package com.manyi.business.order;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.order.bean.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public interface OrderService {

    /**
     * 下单
     * @param request
     */
    Order subscribe(OrderBean request) throws BusinessException, IOException;

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
    void payForOrder(String orderSeqNo , BigDecimal amount , int payChannel ) throws BusinessException;

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
    int queryOrderCount(QueryOrderCondition order);


    /**
     * 条件查询订单
     * @param order
     * @return
     */
    List<Order> queryOrder(QueryOrderCondition order);

    /**
     * 条件查询订单项
     * @param condition
     * @return
     */
    List<OrderItem> queryOrderItem(QueryOrderItemCondition condition);

     /**
     * 订单支付回调接口
     */
    void orderPayCallBack(String orderSeqNo, String amount , int payChannel ,int state) throws BusinessException;


    double queryUserOrderAmountMonthly(int month , long userId , int type ) throws BusinessException;


    double queryUserOrderAmountYearly(int year , long userId , int type ) throws BusinessException;

    public int getOrderCount(QueryOrderCondition condition);

    /**
     * 通过订单号查询订单详情
     * @param orderNo
     * @return
     * @throws BusinessException
     */
    Order queryOrderDetail(String orderNo) throws BusinessException;

    /**
     * 查询未完成订单
     * @param condition
     * @return
     */
    List<Order> queryUnfinishedOrder(QueryOrderCondition condition) throws BusinessException;
}
