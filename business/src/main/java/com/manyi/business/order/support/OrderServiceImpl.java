package com.manyi.business.order.support;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.order.OrderService;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.dao.OrderDao;
import com.manyi.common.util.OrderSeqGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/4.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    public static int SUCCESS= 0;

    @Autowired
    private OrderDao orderDao;


    @Override
    public Order subscribe(Order order) throws BusinessException {

       if(!checkOrderParam(order))
       {
            throw new BusinessException(Type.PARAM_ERROR);
        }
       return createOrder(order);

    }


    private Order createOrder(Order order) throws BusinessException {

        order.setState(OrderStatus.PENDING);
        String orderSeq;
        boolean repeat = true ;
        do
        {
            orderSeq  = OrderSeqGenerator.generateOrderSeq(order.getType());
            Order o = orderDao.getOrderBySeq(orderSeq);
            if( null == o)
            {
                repeat = false;
            }

        }
        while (repeat) ;
        order.setSeqNo(orderSeq);
        List<String> seqs = new ArrayList<String>();
        for(int i =0 ; i<  order.getItemNum(); i++)
        {

            seqs.add(OrderSeqGenerator.generateOrderItemSeq(order.getSeqNo(),i+1));
        }
        order.setItemSeqs(seqs);
        orderDao.createOrder(order);
        return order;
    }

    /**
     * 检查订单参数是否合法
     * @param order
     * @return
     */
    private boolean checkOrderParam(Order order) {
        if(null == order)
        {
            return false;
        }
        else if(null == order.getPaidAmount()
                ||null == order.getPayableAmount()
                ||null == order.getDiscountAmount()
                ||0 == order.getUserId()
                ||0 == order.getItemNum()
                ||0 == order.getType())
            {
              return false;
             }

         int   amount = 0;

        return true;
    }


    @Override
    public void cancelOrder(String orderSeqNo) throws BusinessException {

        if(null == orderSeqNo )
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        Order order = orderDao.getOrderBySeq(orderSeqNo);

        if( null == order)
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        if(!order.getState().equals( OrderStatus.PENDING))
        {
            throw new BusinessException(Type.ORDER_STATUS_ERROR);
        }

        order.setState(OrderStatus.CANCELED);
        orderDao.updateOrder(order);
        List<OrderItem> list = orderDao.queryItemByOrder(order.getId());
        for(OrderItem item : list)
        {
            item.setState(OrderItemStatus.CANCELED);
            orderDao.updateOrderItem(item);
            //++++++下发取消业务事件
        }


    }

    @Override
    public void cancelOrderItem(String itemSeqNo) throws BusinessException {

        if(null == itemSeqNo )
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        OrderItem item = orderDao.getOrderItemBySeq(itemSeqNo);

        if(null == item)
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        item.setState(OrderItemStatus.CANCELED);
        orderDao.updateOrderItem(item);
        Order order = orderDao.getOrderById(item.getOrderId());
         int newPayable =  order.getPayableAmount().intValue() - item.getPayableAmount().intValue();
        order.setPayableAmount(new BigDecimal(newPayable));
        int newPaid =  order.getPaidAmount().intValue() - item.getPayableAmount().intValue();
        order.setPaidAmount(new BigDecimal(newPaid));
        if(newPayable == 0)
        {
            order.setState(OrderStatus.CANCELED);
        }
        orderDao.updateOrder(order);
        //++++++下发取消业务事件

    }

    @Override
    public void payForOrder(String orderSeqNo, BigDecimal amount , int payChannel) throws BusinessException {

        if(null == orderSeqNo )
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        Order order = orderDao.getOrderBySeq(orderSeqNo);

        if( null == order)
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        order.setState(OrderStatus.PAYING);
        orderDao.updateOrder(order);
        List<OrderItem> list = orderDao.queryItemByOrder(order.getId());
        for(OrderItem item : list)
        {
            item.setState(OrderItemStatus.PAYING);
            orderDao.updateOrderItem(item);
        }
    }



    @Override
    public void drawbackOrder(String orderSeqNo) throws BusinessException {

        if(null == orderSeqNo )
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        Order order = orderDao.getOrderBySeq(orderSeqNo);

        if( null == order)
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        if(!order.getState().equals(OrderStatus.PAYED))
        {
            throw new BusinessException(Type.ORDER_STATUS_ERROR);
        }
        order.setState(OrderStatus.DRAWBACK);
        orderDao.updateOrder(order);
        List<OrderItem> list = orderDao.queryItemByOrder(order.getId());
        for(OrderItem item : list)
        {
            item.setState(OrderItemStatus.DRAWBACK);
            orderDao.updateOrderItem(item);
        }
        //++++调用账务退款接口
    }

    @Override
    public QueryOrderResult queryOrder(QueryOrderCondition condition) {

        QueryOrderResult result = new QueryOrderResult();


        if(null != condition && 0 != condition.getPageSize())
        {
            result.setPageNum(condition.getPageNum());
            result.setPageSize(condition.getPageSize());
            result.setPageCount(getOrderCount(condition));
        }
        List<Order>  list = orderDao.queryOrder(condition);
        result.setList(list);
        return result;
    }

    private int getOrderCount(QueryOrderCondition condition) {

        int count =  orderDao.queryOrderPageCount(condition) ;
        int pageCount = (count + condition.getPageSize() -1)/condition.getPageSize();
        return pageCount;
    }

    @Override
    public QueryOrderItemResult queryOrderItem(QueryOrderItemCondition condition)

    {
        QueryOrderItemResult result = new QueryOrderItemResult();


        if(null != condition && 0 != condition.getPageSize())
        {
            result.setPageNum(condition.getPageNum());
            result.setPageSize(condition.getPageSize());
            result.setPageCount(getOrderItemCount(condition));
        }
        List<OrderItem> list = orderDao.queryOrderItem(condition);
        result.setList(list);
        return result;
    }

    private int getOrderItemCount(QueryOrderItemCondition condition) {

        int count =  orderDao.queryOrderItemCount(condition) ;
        int pageCount = (count + condition.getPageSize() -1)/condition.getPageSize();
        return pageCount;
    }


    @Override
    public void orderPayCallBack(String orderSeqNo, BigDecimal amount, int payChannel) throws BusinessException {

        if(null == orderSeqNo || (payChannel !=PayChannel.BANK_CHANNEL && payChannel !=PayChannel.XL_CHANNEL) )
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        Order order = orderDao.getOrderBySeq(orderSeqNo);

        if( null == order)
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }


        if( !order.getState().equals(OrderStatus.PENDING))
        {
            throw new BusinessException(Type.ORDER_STATUS_ERROR);
        }

        if(amount.intValue() == order.getPaidAmount().intValue())
        {
            order.setState(OrderStatus.PAYED);
            order.setPayChannel(payChannel);
            orderDao.updateOrder(order);
            List<OrderItem> list = orderDao.queryItemByOrder(order.getId());
            for(OrderItem item : list)
            {
                item.setState(OrderItemStatus.PAYED);
                orderDao.updateOrderItem(item);
            }
        }
    }


}
