package com.manyi.webext.order.service;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.event.CancelOrderEvent;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.dao.OrderDao;
import com.manyi.business.pay.refund.support.RefundServiceImplTest;
import com.manyi.business.pay.settlement.SettlementService;
import com.manyi.business.pay.settlement.support.SettlementServiceImplTest;
import com.manyi.common.util.DateUtil;
import com.manyi.common.util.OrderSeqGenerator;
import com.manyi.webext.order.bean.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/16.
 */
@Service("testOrderService")
public class TestOrderServiceImpl implements TestOrderService{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RefundServiceImplTest refundServiceImplTest;


    @Autowired
    private SettlementServiceImplTest settlementService;

    private static String RES_REC = "received";


    @Override
    public Order subscribeTest(OrderBean request) throws BusinessException, IOException {

        Order order = new Order();
        order.setState(OrderStatus.PENDING);
        order.setUserId(request.getUserId());
        order.setPaidAmount(new BigDecimal(request.getPaidAmount()));
        order.setDiscountAmount(new BigDecimal(0));
        order.setPayableAmount(new BigDecimal(request.getPaidAmount()));;
        order.setType(OrderType.FUND_GUARANTEE);
        String orderSeq;
        boolean repeat = true;
        do {
            orderSeq = OrderSeqGenerator.generateOrderSeq(order.getType());
            Order o = orderDao.getOrderBySeq(orderSeq);
            if (null == o) {
                repeat = false;
            }

        }
        while (repeat);
        order.setSeqNo(orderSeq);
        order.setCreateTime(DateUtil.getCurrentString(new Date()));
        orderDao.createOrder(order);

        OrderItem itemGuarantee = new OrderItem();
        itemGuarantee.setSeqNo(OrderSeqGenerator.generateOrderItemSeq(order.getSeqNo(), 1));
        itemGuarantee.setPayableAmount(order.getPaidAmount());
        itemGuarantee.setOrderId(order.getId());
        itemGuarantee.setUserId(order.getUserId());
        itemGuarantee.setDescription(request.getItemNum()+"");
        itemGuarantee.setType(BusinessType.FUND_GUARANTEE);
        itemGuarantee.setState(OrderItemStatus.PENDING);
        itemGuarantee.setCreateTime(DateUtil.getCurrentString(new Date()));
        orderDao.createOrderItem(itemGuarantee);
        return order;
    }

    @Override
    public void payForDriver(Test request) throws BusinessException, IOException {

        Order o =  orderDao.getOrderBySeq(request.getTest());
        settlementService.settlement(o.getSeqNo(), o.getUserId() ,o.getPaidAmount().toString());
        o.setState(OrderStatus.SUCCESS);
        orderDao.updateOrder(o);

    }

    @Override
    public void refundOrder(Order request) throws BusinessException, IOException {

        Order order =  orderDao.getOrderBySeq(request.getSeqNo()) ;
        String result =  refundServiceImplTest.orderRefund(order.getSeqNo(), order.getUserId(), order.getPaidAmount().toString());
        if(result.equals(RES_REC))
        {
            order.setState(OrderStatus.DRAWBACK);
            orderDao.updateOrder(order);
            List<OrderItem> list = orderDao.queryItemByOrder(order.getId());
            for (OrderItem item : list) {
            item.setState(OrderItemStatus.DRAWBACK);
            orderDao.updateOrderItem(item);
        }
        }
    }
}
