package com.manyi.business.order.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.etc.ETCService;
import com.manyi.business.etc.bean.Etc;
import com.manyi.business.etc.bean.EtcInvoice;
import com.manyi.business.event.CancelOrderEvent;
import com.manyi.business.order.OrderService;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.dao.OrderDao;
import com.manyi.common.util.DateUtil;
import com.manyi.common.util.NumberValidationUtils;
import com.manyi.common.util.OrderSeqGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


/**
 * @Description: 订单实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    /*
      1月
     */
    public static final  int JANUARY = 1;

    /*
    12月
   */
    public   static final  int DECEMBER = 12;


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ETCService etcService;


    @Autowired
    private ApplicationContext applicationContext;

    private  final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Order subscribe(OrderBean req) throws BusinessException, IOException {

        if (!checkOrderParam(req)) {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        return createOrder(req);

    }


    private Order createOrder(OrderBean request) throws BusinessException, IOException {

        Order order = new Order();
        order.setState(OrderStatus.PENDING);
        order.setUserId(request.getUserId());
        order.setPaidAmount(new BigDecimal(request.getPaidAmount()));
        order.setDiscountAmount(new BigDecimal(request.getDiscountAmount()));
        order.setPayableAmount(new BigDecimal(request.getPayableAmount()));
        if (request.getType() != OrderType.ETC_TYPE) {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        order.setType(request.getType());
        String orderSeq = createUnRepeatSeq(order);
        order.setSeqNo(orderSeq);
        order.setCreateTime(DateUtil.getCurrentString(new Date()));
        createOrderDes(order,request);
        orderDao.createOrder(order);
        List<OrderItem>  itemList =  createItem(request ,order);
        order.setItemList(itemList);
        return order;
    }

    private String createUnRepeatSeq(Order order) throws BusinessException {
        String orderSeq;
        boolean repeat = true;
        do {
            orderSeq = OrderSeqGenerator.generateOrderSeq(order.getType());
            Order order1 = orderDao.getOrderBySeq(orderSeq);
            if (StringUtils.isEmpty(order1) ) {
                repeat = false;
            }
        }
        while (repeat);
        return orderSeq;
    }

    private List<OrderItem> createItem(OrderBean request, Order order) throws IOException, BusinessException {
        List<OrderItem> itemList = new ArrayList<OrderItem>();
        int count = 1;
        for (String itemJson : request.getItemJsons()) {
            Map maps = objectMapper.readValue(itemJson, Map.class);
            String type = (String) maps.get("type");
            if (type.equals(BusinessType.ETC)) {
                Etc etc = new Etc();
                etc.setSeqNo(OrderSeqGenerator.generateOrderItemSeq(order.getSeqNo(), count));
                etc.setETCCode((String) maps.get("ETCCode"));
                etc.setPlateNum((String) maps.get("plateNum"));
                if (!NumberValidationUtils.isRealNumber((String) maps.get("payableAmount"))) {
                    throw new BusinessException(Type.PARAM_ERROR);
                }
                etc.setPayableAmount(new BigDecimal((String) maps.get("payableAmount")));
                etc.setOrderId(order.getId());
                etc.setUserId(order.getUserId());
                OrderItem item = etcService.createETCOrder(etc);
                item.setDescription(null);
                itemList.add(item);

            } else if (type.equals(BusinessType.ETC_INVOICE)) {
                EtcInvoice invoice = new EtcInvoice();
                invoice.setPhone((String) maps.get("phone"));
                invoice.setInvoiceHeader((String) maps.get("invoiceHeader"));
                if (!NumberValidationUtils.isRealNumber((String) maps.get("payableAmount"))
                        || !NumberValidationUtils.isRealNumber((String) maps.get("amount"))) {
                    throw new BusinessException(Type.PARAM_ERROR);
                }
                invoice.setAmount(new BigDecimal((String) maps.get("amount")));
                invoice.setPayableAmount(new BigDecimal((String) maps.get("payableAmount")));
                invoice.setPostAddress((String) maps.get("postAddress"));
                invoice.setAddressee((String) maps.get("addressee"));
                invoice.setOrderId(order.getId());
                invoice.setUserId(order.getUserId());
                invoice.setSeqNo(OrderSeqGenerator.generateOrderItemSeq(order.getSeqNo(), count));
                OrderItem item = etcService.createETCInvoiceOrder(invoice);
                item.setDescription(null);
                itemList.add(item);
            }
            count++;
        }
        return itemList;
    }

    private void createOrderDes(Order order, OrderBean bean) throws IOException {

        if (bean.getType() == OrderType.ETC_TYPE) {
            int amount = 0 ;
            for (String itemJson : bean.getItemJsons()) {
                Map maps = objectMapper.readValue(itemJson, Map.class);
                String type = (String) maps.get("type");
                if (type.equals(BusinessType.ETC)) {
                    amount++;
                }
            }
            String des = "鲁通卡预充值，共充值"+amount+"张卡";
            order.setDescription(des);
        }
    }

    /**
     * 检查订单参数是否合法
     *
     * @param order
     * @return
     */
    private boolean checkOrderParam(OrderBean order) {
        if (null == order) {
            return false;
        } else if (null == order.getPaidAmount()
                || null == order.getPayableAmount()
                || null == order.getDiscountAmount()
                || 0 == order.getUserId()
                || 0 == order.getType()
                || null == order.getItemJsons()) {
            return false;
        }
        if (!NumberValidationUtils.isRealNumber(order.getPaidAmount()) ||
                !NumberValidationUtils.isRealNumber(order.getPayableAmount())
                || !NumberValidationUtils.isRealNumber(order.getDiscountAmount())) {
            return false;
        }


        return true;
    }


    @Override
    public void cancelOrder(String orderSeqNo) throws BusinessException {

        if (null == orderSeqNo) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        Order order = orderDao.getOrderBySeq(orderSeqNo);

        if (null == order) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        if (!order.getState().equals(OrderStatus.PENDING)) {
            throw new BusinessException(Type.ORDER_STATUS_ERROR);
        }

        order.setState(OrderStatus.CANCELED);
        orderDao.updateOrder(order);
        List<OrderItem> list = orderDao.queryItemByOrder(order.getId());
        for (OrderItem item : list) {
            item.setState(OrderItemStatus.CANCELED);
            orderDao.updateOrderItem(item);
            //下发取消业务事件
            applicationContext.publishEvent(new CancelOrderEvent(item));
        }

    }

    @Override
    public void cancelOrderItem(String itemSeqNo) throws BusinessException {

        if (null == itemSeqNo) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        OrderItem item = orderDao.getOrderItemBySeq(itemSeqNo);

        if (null == item) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        item.setState(OrderItemStatus.CANCELED);
        orderDao.updateOrderItem(item);
        Order order = orderDao.getOrderById(item.getOrderId());
        int newPayable = order.getPayableAmount().intValue() - item.getPayableAmount().intValue();
        order.setPayableAmount(new BigDecimal(newPayable));
        int newPaid = order.getPaidAmount().intValue() - item.getPayableAmount().intValue();
        order.setPaidAmount(new BigDecimal(newPaid));
        if (newPayable == 0) {
            order.setState(OrderStatus.CANCELED);
        }
        orderDao.updateOrder(order);

    }

    @Override
    public void payForOrder(String orderSeqNo, BigDecimal amount, int payChannel) throws BusinessException {

        if (null == orderSeqNo) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        Order order = orderDao.getOrderBySeq(orderSeqNo);

        if (null == order) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        order.setState(OrderStatus.PAYING);
        orderDao.updateOrder(order);
        List<OrderItem> list = orderDao.queryItemByOrder(order.getId());
        for (OrderItem item : list) {
            item.setState(OrderItemStatus.PAYING);
            orderDao.updateOrderItem(item);
        }
    }


    @Override
    public void drawbackOrder(String orderSeqNo) throws BusinessException {

        if (null == orderSeqNo) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        Order order = orderDao.getOrderBySeq(orderSeqNo);

        if (null == order) {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        if (!order.getState().equals(OrderStatus.PAYED)) {
            throw new BusinessException(Type.ORDER_STATUS_ERROR);
        }
        order.setState(OrderStatus.DRAWBACK);
        orderDao.updateOrder(order);
        List<OrderItem> list = orderDao.queryItemByOrder(order.getId());
        for (OrderItem item : list) {
            item.setState(OrderItemStatus.DRAWBACK);
            orderDao.updateOrderItem(item);
        }
        //++++调用账务退款接口
    }

    @Override
    public int queryOrderCount(QueryOrderCondition order) {

        return orderDao.queryOrderPageCount(order);
    }

    @Override
    public List<Order> queryOrder(QueryOrderCondition condition) {

        if (null != condition && 0 != condition.getPageSize() && condition.getPageNum() > 0) {
            condition.setPageNum((condition.getPageNum() - 1) * condition.getPageSize());
        }

        return orderDao.queryOrder(condition);

    }

    @Override
    public int getOrderCount(QueryOrderCondition condition) {

        int count = orderDao.queryOrderPageCount(condition);
        int pageCount = (count + condition.getPageSize() - 1) / condition.getPageSize();
        return pageCount;
    }

    @Override
    public List<OrderItem> queryOrderItem(QueryOrderItemCondition condition)

    {
        if (null != condition && null != condition.getOrderSeq()) {
            Order order = orderDao.getOrderBySeq(condition.getOrderSeq());
            condition.setOrderId(order.getId());
        }

        return orderDao.queryOrderItem(condition);
    }


    @Override
    public void orderPayCallBack(String orderSeqNo, String amount, int payChannel,int state) throws BusinessException {

        if(!checkPayBackParam(orderSeqNo,payChannel,amount))
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        Order order = orderDao.getOrderBySeq(orderSeqNo);

        if (null == order) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        if (!order.getState().equals(OrderStatus.PENDING)) {
            throw new BusinessException(Type.ORDER_STATUS_ERROR);
        }

        if(state != 0)
        {
            order.setState(OrderStatus.PAY_FAILED);
            order.setPayChannel(payChannel);
            orderDao.updateOrder(order);

        }
        BigDecimal value = new BigDecimal(amount);
        if (value.compareTo(order.getPaidAmount()) == 0) {
            order.setState(OrderStatus.PAYED);
            order.setPayChannel(payChannel);
            orderDao.updateOrder(order);
            List<OrderItem> list = orderDao.queryItemByOrder(order.getId());
            for (OrderItem item : list) {
                item.setState(OrderItemStatus.PAYED);
                orderDao.updateOrderItem(item);
            }
        } else {
            throw new BusinessException(Type.PARAM_ERROR);
        }

    }

    private boolean checkPayBackParam(String orderSeqNo, int payChannel, String amount) {

        if (null == orderSeqNo || (payChannel != PayChannel.BANK_CHANNEL
                && payChannel != PayChannel.XL_CHANNEL)) {

            return false;
        }
       else if (!NumberValidationUtils.isRealNumber(amount)) {
            return false;
        }
        else
        {
            return true;
        }

    }

    @Override
    public double queryUserOrderAmountMonthly(int month, long userId, int type) throws BusinessException {
        if (month < JANUARY || month > DECEMBER || userId == 0 || type == 0) {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        String startTime = DateUtil.getMonthStart(month);
        String endTime = DateUtil.getMonthEnd(month);
        Double amount = orderDao.computeOrderAmount(startTime, endTime, userId, type);
        if (null != amount) {
            return amount;
        } else {
            return 0;
        }

    }

    @Override
    public double queryUserOrderAmountYearly(int year, long userId, int type) throws BusinessException {

        if (userId == 0 || type == 0) {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        String startTime = DateUtil.getYearStart(year);
        String endTime = DateUtil.getYearEnd(year);
        Double amount = orderDao.computeOrderAmount(startTime, endTime, userId, type);

        if (null != amount) {
            return amount;
        } else {
            return 0;
        }
    }

    @Override
    public Order queryOrderDetail(String orderNo) throws BusinessException {
        return null;
    }

    @Override
    public List<Order> queryUnfinishedOrder(QueryOrderCondition condition) throws BusinessException {
        if(StringUtils.isEmpty(condition))
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        if (0 != condition.getPageSize() && condition.getPageNum() > 0) {
            condition.setPageNum((condition.getPageNum() - 1) * condition.getPageSize());
        }
        condition.setState(OrderStatus.SUCCESS);
        return orderDao.queryUnfinishedOrder(condition);
    }

}
