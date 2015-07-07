package com.manyi.business.order.support.dao;

import com.manyi.business.order.bean.Order;
import com.manyi.business.order.bean.OrderItem;
import com.manyi.business.order.bean.QueryOrderCondition;
import com.manyi.business.order.bean.QueryOrderItemCondition;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public interface OrderDao {

    /**
     *查看相应序列号的订单是否存在
     * @return
     */
    Order getOrderBySeq(@Param("seqNo") String seqNo);

    /**
     *查看相应序列号的订单是否存在
     * @return
     */
    Order getOrderById(@Param("id") long id);

    /**
     * 查看相应订单项号的订单项是否存在
     * @param seqNo
     * @return
     */
    OrderItem getOrderItemBySeq(@Param("seqNo") String seqNo);

    /**
     * 创建订单
     * @param order
     */
    void createOrder(Order order);


    /**
     * 创建订单项
     * @param item
     */
    void createOrderItem(OrderItem item);

    /**
     * 更新订单
     * @param order
     */
    void updateOrder(Order order);


    /**
     * 更新订单项
     * @param item
     */
    void updateOrderItem(OrderItem item);

    /**
     * 根据订单查询订单项
     * @param orderId
     * @return
     */
    List<OrderItem> queryItemByOrder(@Param("orderId") long orderId);

    /**
     *
     * 条件查询订单
     * @param condition
     * @return
     */
    List<Order> queryOrder(QueryOrderCondition condition);

    /**
     * 条件查询订单项
     * @param item
     * @return
     */
    List<OrderItem> queryOrderItem(QueryOrderItemCondition item);

    /**
     * 查询订单数量
     * @param condition
     * @return
     */
    Integer queryOrderPageCount(QueryOrderCondition condition);

    /**
     * 查询订单项数量
     * @param condition
     * @return
     */
    Integer queryOrderItemCount(QueryOrderItemCondition condition);


    /**
     *查看相应序列号的订单是否存在
     * @return
     */
    OrderItem getOrderItemById(@Param("id") long id);

    /**
     * 根据时间计算订单费用
     * @param startTime
     * @param endTime
     * @param userId
     * @param type
     * @return
     */
    Double computeOrderAmount(@Param("startTime")String startTime,@Param("endTime") String endTime, @Param("userId")long userId, @Param("type")int type);

    /**
     * 查询未完成订单
     * @param condition
     * @return
     */
    List<Order> queryUnfinishedOrder(QueryOrderCondition condition);
}
