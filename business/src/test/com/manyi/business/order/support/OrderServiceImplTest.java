package com.manyi.business.order.support;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.etc.bean.ETCPreChargeRequest;
import com.manyi.business.etc.bean.Etc;
import com.manyi.business.etc.bean.EtcInvoice;
import com.manyi.business.etc.bean.EtcResult;
import com.manyi.business.etc.support.ETCServiceImpl;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.dao.OrderDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-business-servlet.xml"})
//@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
//@Transactional
public class OrderServiceImplTest {


    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ETCServiceImpl etcService;

    @Autowired
    private OrderDao orderDao;

    Order order = new Order();
    OrderItem item1 = new OrderItem();
    OrderItem item2 = new OrderItem();
    @Before
    public void setUp() throws Exception {

        order.setUserId(101);
        order.setDiscountAmount(new BigDecimal(0));
        order.setPaidAmount(new BigDecimal(1000));
        order.setPayableAmount(new BigDecimal(1000));
        order.setType(10);
        order.setItemNum(4);
    }

    @Test
    public void testSubscribe() throws Exception {
        Order orderRes = orderService.subscribe(order);
        Order order1 = orderDao.getOrderBySeq(order.getSeqNo());
        assertNotNull(order1);
        assertEquals(1000,order1.getPaidAmount().intValue());
        assertEquals(orderRes.getItemSeqs().size(),4) ;

    }

    @Test
    public void testSubscribeExceptionNull() {
        //空指针异常
        try
        {
            orderService.subscribe(null);
        } catch (Exception e) {
            assertTrue(e instanceof BusinessException);
        }
    }

    @Test
    public void testCreateETCOrder()   throws Exception
    {
        Order orderRes = orderService.subscribe(order);
        List<Etc> list = new ArrayList<Etc>();
        int i = 0;
         for(String id : orderRes.getItemSeqs())
         {
             Etc etc = new Etc();
             etc.setETCCode("650010203010"+i);
             etc.setPlateNum("鲁A 12456"+i);
             etc.setSeqNo(id);
             etc.setOrderId(orderRes.getId());
             etc.setUserId(orderRes.getUserId());
             etc.setPayableAmount(new BigDecimal(100));
             i++;
             list.add(etc);
         }
        etcService.createETCOrder(list);
        QueryOrderItemCondition item = new QueryOrderItemCondition();
        item.setOrderId(orderRes.getId());
        List<OrderItem> itemlist =  orderDao.queryOrderItem(item);
        assertEquals(itemlist.size(),4);

    }


    @Test
    public void testCreateETCInvoiceOrder()   throws Exception
    {
        Order orderRes = orderService.subscribe(order);

            EtcInvoice etc = new EtcInvoice();
            etc.setPhone("18680333333");
            etc.setInvoiceHeader("满易网科技有限公司");
            etc.setPostAddress("山东省济南市历下区奥体北路山东高速大厦 刘凯华收");
            etc.setAmount(new BigDecimal(1000));
            etc.setSeqNo(orderRes.getItemSeqs().get(1));
            etc.setOrderId(orderRes.getId());
            etc.setUserId(orderRes.getUserId());
            etc.setPayableAmount(new BigDecimal(100));

        etcService.createETCInvoiceOrder(etc);
        QueryOrderItemCondition item = new QueryOrderItemCondition();
        item.setOrderId(orderRes.getId());
        List<OrderItem> itemlist =  orderDao.queryOrderItem(item);
        assertEquals(itemlist.size(),1);

    }


    @Test
    public void testCancelOrder() throws Exception {

      Order orderRes =  orderService.subscribe(order);

        List<Etc> list = new ArrayList<Etc>();
        int i = 0;
        for(String id : orderRes.getItemSeqs())
        {
            Etc etc = new Etc();
            etc.setETCCode("650010203010"+i);
            etc.setPlateNum("鲁A 12456"+i);
            etc.setSeqNo(id);
            etc.setOrderId(orderRes.getId());
            etc.setUserId(orderRes.getUserId());
            etc.setPayableAmount(new BigDecimal(100));
            i++;
            list.add(etc);
        }
        etcService.createETCOrder(list);

        orderService.cancelOrder(order.getSeqNo());
        Order order1 = orderDao.getOrderBySeq(order.getSeqNo());
        assertNotNull(order1);
        assertEquals(OrderStatus.CANCELED,order1.getState());
        List<OrderItem> list1 = orderDao.queryItemByOrder(order.getId()) ;
        assertNotNull(list1);
        assertEquals(list1.size(),4);
        for (OrderItem item : list1)
        {
            assertEquals(OrderItemStatus.CANCELED,item.getState());
        }

    }






//    @Test
//    public void testCancelOrderItemPart() throws Exception {
//
//
//        orderService.subscribe(order);
//        orderService.cancelOrderItem(item1.getSeqNo());
//        OrderItem itemCancel1 = orderDao.getOrderItemBySeq(item1.getSeqNo());
//        assertEquals(OrderItemStatus.CANCELED,itemCancel1.getState());
//        Order order1 =  orderDao.getOrderById(itemCancel1.getOrderId());
//        assertEquals(OrderItemStatus.PENDING,order1.getState());
//        assertEquals(200,order1.getPaidAmount().intValue());
//        assertEquals(200,order1.getPayableAmount().intValue());
//
//        orderService.cancelOrderItem(item2.getSeqNo());
//        Order order2 =  orderDao.getOrderById(item2.getOrderId());
//        assertEquals(0,order2.getPaidAmount().intValue());
//        assertEquals(0,order2.getPayableAmount().intValue());
//        assertEquals(OrderStatus.CANCELED,order2.getState());
//    }


    @Test
    public void testPayForOrder() throws Exception {
        orderService.subscribe(order);
        orderService.payForOrder(order.getSeqNo(),new BigDecimal(1000),2);

        Order order1 = orderDao.getOrderBySeq(order.getSeqNo());
        assertNotNull(order1);
        assertEquals(OrderStatus.PAYING,order1.getState());
        List<OrderItem> list1 = orderDao.queryItemByOrder(order.getId()) ;
        assertNotNull(list1);
        assertEquals(list1.size(),2);
        for (OrderItem item : list1)
        {
            assertEquals(OrderItemStatus.PAYING,item.getState());
        }

        orderService.orderPayCallBack(order.getSeqNo(),new BigDecimal(1000),1);

        Order order2 = orderDao.getOrderBySeq(order.getSeqNo());
        assertNotNull(order2);
        assertEquals(OrderStatus.PAYED,order2.getState());
        List<OrderItem> list2 = orderDao.queryItemByOrder(order.getId()) ;
        assertNotNull(list2);
        assertEquals(list2.size(),2);
        for (OrderItem item : list2)
        {
            assertEquals(OrderItemStatus.PAYED,item.getState());
        }

    }

//    @Test
//    public void testOrderPayCallBack() throws Exception {
//        orderService.subscribe(order);
//        orderService.orderPayCallBack(order.getSeqNo(),new BigDecimal(1000));
//
//        Order order1 = orderDao.getOrderBySeq(order.getSeqNo());
//        assertNotNull(order1);
//        assertEquals(OrderStatus.PAYYED,order1.getState());
//        List<OrderItem> list1 = orderDao.queryItemByOrder(order.getId()) ;
//        assertNotNull(list1);
//        assertEquals(list1.size(),2);
//        for (OrderItem item : list1)
//        {
//            assertEquals(OrderItemStatus.PAYYED,item.getState());
//        }
//
//    }

//    @Test
//    public void testDrawbackOrder() throws Exception {
//
//        orderService.subscribe(order);
//        orderService.payForOrder(order.getSeqNo(),new BigDecimal(1000));
//        orderService.orderPayCallBack(order.getSeqNo(),new BigDecimal(1000));
//        orderService.drawbackOrder(order.getSeqNo());
//        Order order1 = orderDao.getOrderBySeq(order.getSeqNo());
//        assertNotNull(order1);
//        assertEquals(OrderStatus.DRAWBACK,order1.getState());
//        List<OrderItem> list1 = orderDao.queryItemByOrder(order.getId()) ;
//        assertNotNull(list1);
//        assertEquals(list1.size(),2);
//        for (OrderItem item : list1)
//        {
//            assertEquals(OrderItemStatus.DRAWBACK,item.getState());
//        }
//
//
//    }

    @Test
    public void testQueryOrder() throws Exception {

        Order order1 = new Order();

        order1.setUserId(101);
        order1.setDiscountAmount(new BigDecimal(0));
        order1.setPaidAmount(new BigDecimal(1000));
        order1.setPayableAmount(new BigDecimal(1000));
        order1.setType(10);
        order1.setItemNum(4);
        orderService.subscribe(order1);

        Order order2 = new Order();
        order2.setUserId(102);
        order2.setDiscountAmount(new BigDecimal(0));
        order2.setPaidAmount(new BigDecimal(1000));
        order2.setPayableAmount(new BigDecimal(1000));
        order2.setType(10);
        order2.setItemNum(4);
        orderService.subscribe(order2);


        Order order3 = new Order();
        order3.setUserId(102);
        order3.setDiscountAmount(new BigDecimal(0));
        order3.setPaidAmount(new BigDecimal(1000));
        order3.setPayableAmount(new BigDecimal(1000));
        order3.setType(10);
        order3.setItemNum(4);
        orderService.subscribe(order3);

        Order order4 = new Order();
        order4.setUserId(102);
        order4.setDiscountAmount(new BigDecimal(0));
        order4.setPaidAmount(new BigDecimal(1000));
        order4.setPayableAmount(new BigDecimal(1000));
        order4.setType(20);
        order4.setItemNum(4);
        orderService.subscribe(order4);
        orderService.cancelOrder(order4.getSeqNo());


        Order order5 = new Order();
        order5.setUserId(105);
        order5.setDiscountAmount(new BigDecimal(0));
        order5.setPaidAmount(new BigDecimal(1000));
        order5.setPayableAmount(new BigDecimal(1000));
        order5.setType(10);
        order5.setItemNum(4);
        orderService.subscribe(order5);


        Order order6 = new Order();
        order6.setUserId(106);
        order6.setDiscountAmount(new BigDecimal(0));
        order6.setPaidAmount(new BigDecimal(1000));
        order6.setPayableAmount(new BigDecimal(1000));
        order6.setType(10);
        order6.setItemNum(4);
        orderService.subscribe(order6);
        orderService.payForOrder(order6.getSeqNo(),new BigDecimal(1000),1);

        Order order7 = new Order();
        order7.setUserId(107);
        order7.setDiscountAmount(new BigDecimal(0));
        order7.setPaidAmount(new BigDecimal(1000));
        order7.setPayableAmount(new BigDecimal(1000));
        order7.setType(10);
        order7.setItemNum(4);
        orderService.subscribe(order7);
        orderService.payForOrder(order7.getSeqNo(),new BigDecimal(1000),1);
//
        QueryOrderCondition condition = new  QueryOrderCondition();
        QueryOrderResult result = orderService.queryOrder(condition);
        assertEquals(result.getList().size(),7);


        condition.setPageNum(0);
        condition.setPageSize(3);
        QueryOrderResult result1 = orderService.queryOrder(condition);
        assertEquals(result1.getList().size(),3);
        assertEquals(result1.getPageCount(),3);

        condition.setState(OrderStatus.PENDING);
        QueryOrderResult result2 = orderService.queryOrder(condition);
        assertEquals(result2.getList().size(),3);
        assertEquals(result2.getPageCount(),2);


        condition.setState(OrderStatus.PAYING);
        QueryOrderResult result3 = orderService.queryOrder(condition);
        assertEquals(result3.getList().size(),2);
        assertEquals(result3.getPageCount(),1);

        condition.setState(OrderStatus.PENDING);
        condition.setUserId(102);
        QueryOrderResult result4 = orderService.queryOrder(condition);
        assertEquals(result4.getList().size(),2);
        assertEquals(result4.getPageCount(),1);

        condition.setState(OrderStatus.PENDING);
        condition.setUserId(101);
        condition.setSeqNo(order1.getSeqNo());
        QueryOrderResult result5 = orderService.queryOrder(condition);
        assertEquals(result5.getList().size(),1);
        assertEquals(result5.getPageCount(),1);


        condition.setState(OrderStatus.PENDING);
        condition.setUserId(103);
        condition.setSeqNo(order1.getSeqNo());
        QueryOrderResult result6 = orderService.queryOrder(condition);
        assertEquals(result6.getList().size(),0);
        assertEquals(result6.getPageCount(),0);

        condition.setState(OrderStatus.PENDING);
        condition.setUserId(0);
//        condition.setSeqNo(order1.getSeqNo());
        condition.setSeqNo(null);
        condition.setType(10);
        condition.setPageNum(3);
        QueryOrderResult result7 = orderService.queryOrder(condition);
        assertEquals(result7.getList().size(),1);
        assertEquals(result7.getPageCount(),2);

    }

    @Test
    public void  testOrderCallBack() throws BusinessException

    {

        Order orderRes = orderService.subscribe(order);
        List<Etc> list = new ArrayList<Etc>();
        int i = 0;
        for(String id : orderRes.getItemSeqs())
        {
            Etc etc = new Etc();
            etc.setETCCode("650010203010"+i);
            etc.setPlateNum("鲁A 12456"+i);
            etc.setSeqNo(id);
            etc.setOrderId(orderRes.getId());
            etc.setUserId(orderRes.getUserId());
            etc.setPayableAmount(new BigDecimal(100));
            i++;
            list.add(etc);
        }
        etcService.createETCOrder(list);
        orderService.orderPayCallBack(orderRes.getSeqNo(),new BigDecimal(1000),PayChannel.XL_CHANNEL);
        Order o1 = orderDao.getOrderById(orderRes.getId());
        QueryOrderItemCondition item = new QueryOrderItemCondition();
        item.setOrderId(orderRes.getId());
        List<OrderItem> itemlist =  orderDao.queryOrderItem(item);
        assertEquals(itemlist.size(),4);
        assertEquals(OrderStatus.PAYED,o1.getState());
        for(OrderItem info : itemlist)
        {
            assertEquals(OrderItemStatus.PAYED,info.getState());
        }
    }


    @Test
    public void  testETCPreCallBack() throws BusinessException

    {

        Order orderRes = orderService.subscribe(order);
        List<Etc> list = new ArrayList<Etc>();
        int i = 0;
        for(String id : orderRes.getItemSeqs())
        {
            Etc etc = new Etc();
            etc.setETCCode("650010203010"+i);
            etc.setPlateNum("鲁A 12456"+i);
            etc.setSeqNo(id);
            etc.setOrderId(orderRes.getId());
            etc.setUserId(orderRes.getUserId());
            etc.setPayableAmount(new BigDecimal(100));
            i++;
            list.add(etc);
        }
        etcService.createETCOrder(list);
        orderService.orderPayCallBack(orderRes.getSeqNo(),new BigDecimal(1000),PayChannel.XL_CHANNEL);

        ETCPreChargeRequest request = new ETCPreChargeRequest() ;
        request.setOrderSeq(orderRes.getSeqNo());
        request.setState("success");
        List<EtcResult> backlist = new ArrayList<EtcResult>();
        for( String item : orderRes.getItemSeqs())
        {
            EtcResult result = new EtcResult();
            result.setOrderItemSeq(item);
            result.setState("success");
            backlist.add(result);
        }

        request.setList(backlist);
        etcService.preChargeETCCallBack(request);

        Order o1 = orderDao.getOrderById(orderRes.getId());
        QueryOrderItemCondition item = new QueryOrderItemCondition();
        item.setOrderId(orderRes.getId());
        List<OrderItem> itemlist =  orderDao.queryOrderItem(item);
        assertEquals(itemlist.size(),4);
        assertEquals(OrderStatus.SUCCESS,o1.getState());
        for(OrderItem info : itemlist)
        {
            assertEquals(OrderItemStatus.SUCCESS,info.getState());
        }

    }


    @Test
    public void  testETCPreCallBackFailed() throws BusinessException

    {

        Order orderRes = orderService.subscribe(order);
        List<Etc> list = new ArrayList<Etc>();
        int i = 0;
        for(String id : orderRes.getItemSeqs())
        {
            Etc etc = new Etc();
            etc.setETCCode("650010203010"+i);
            etc.setPlateNum("鲁A 12456"+i);
            etc.setSeqNo(id);
            etc.setOrderId(orderRes.getId());
            etc.setUserId(orderRes.getUserId());
            etc.setPayableAmount(new BigDecimal(100));
            i++;
            list.add(etc);
        }
        etcService.createETCOrder(list);
        orderService.orderPayCallBack(orderRes.getSeqNo(),new BigDecimal(1000),PayChannel.XL_CHANNEL);

        ETCPreChargeRequest request = new ETCPreChargeRequest() ;
        request.setOrderSeq(orderRes.getSeqNo());
        request.setState("success");
        List<EtcResult> backlist = new ArrayList<EtcResult>();
        for( String item : orderRes.getItemSeqs())
        {
            EtcResult result = new EtcResult();
            result.setOrderItemSeq(item);
            result.setState("success");
            backlist.add(result);
        }
        backlist.get(0).setState("failed");
        request.setList(backlist);
        etcService.preChargeETCCallBack(request);

        Order o1 = orderDao.getOrderById(orderRes.getId());
        QueryOrderItemCondition item = new QueryOrderItemCondition();
        item.setOrderId(orderRes.getId());
        List<OrderItem> itemlist =  orderDao.queryOrderItem(item);
        assertEquals(itemlist.size(),4);
        assertEquals(OrderStatus.PAYED,o1.getState());

    }

//    @Test
//    public void testQueryOrderItem() throws Exception {
//        OrderItem item3 = new OrderItem();
//        OrderItem item4 = new OrderItem();
//        OrderItem item5 = new OrderItem();
//        OrderItem item6 = new OrderItem();
//        item3.setPayableAmount(new BigDecimal(800));
//        item3.setType(BusinessType.INVOICE);
//        item4.setPayableAmount(new BigDecimal(200));
//        item4.setType(BusinessType.ETC);
//        item5.setPayableAmount(new BigDecimal(800));
//        item5.setType(BusinessType.INVOICE);
//        item6.setPayableAmount(new BigDecimal(200));
//        item6.setType(BusinessType.ETC);
//        order.getItemList().add(item3);
//        order.getItemList().add(item4);
//        order.getItemList().add(item5);
//        order.getItemList().add(item6);
//        order.setPaidAmount(new BigDecimal(3000));
//        order.setPayableAmount(new BigDecimal(3000));
//        orderService.subscribe(order);
//
//        Order order1 = new Order();
//        order1.setUserId(102);
//        order1.setDiscountAmount(new BigDecimal(0));
//        order1.setPaidAmount(new BigDecimal(1000));
//        order1.setPayableAmount(new BigDecimal(1000));
//        List<OrderItem> list1 = new ArrayList<OrderItem>();
//        list1.add(item1);
//        list1.add(item2);
//        order1.setItemList(list1);
//        orderService.subscribe(order1);
//        orderService.payForOrder(order1.getSeqNo(),new BigDecimal(1000));
//
//
//        QueryOrderItemCondition condition = new QueryOrderItemCondition();
//        QueryOrderItemResult result = orderService.queryOrderItem(condition);
//        assertEquals(result.getList().size(),8);
//        assertEquals(result.getPageCount(),0);
//
//        condition.setState(OrderItemStatus.PENDING);
//        condition.setPageNum(0);
//        condition.setPageSize(3);
//        QueryOrderItemResult result1 = orderService.queryOrderItem(condition);
//        assertEquals(result1.getList().size(),3);
//        assertEquals(result1.getPageCount(),6);
//
//        condition.setState(null);
//        condition.setOrderId(order.getId());
//        condition.setPageNum(0);
//        condition.setPageSize(3);
//        QueryOrderItemResult result2 = orderService.queryOrderItem(condition);
//        assertEquals(result2.getList().size(),3);
//        assertEquals(result2.getPageCount(),6);
//
//
//        condition.setOrderId(order.getId());
//        condition.setUserId(102);
//        condition.setPageNum(0);
//        condition.setPageSize(3);
//        QueryOrderItemResult result3 = orderService.queryOrderItem(condition);
//        assertEquals(result3.getList().size(),0);
//        assertEquals(result3.getPageCount(),0);
//
//
//        condition.setOrderId(0);
//        condition.setUserId(102);
//        condition.setPageNum(0);
//        condition.setPageSize(3);
//        QueryOrderItemResult result4 = orderService.queryOrderItem(condition);
//        assertEquals(result4.getList().size(),2);
//        assertEquals(result4.getPageCount(),2);
//
//
//        condition.setSeqNo(item1.getSeqNo());
//        condition.setUserId(0);
//        condition.setPageNum(0);
//        condition.setPageSize(3);
//        QueryOrderItemResult result5 = orderService.queryOrderItem(condition);
//        assertEquals(result5.getList().size(),1);
//        assertEquals(result5.getPageCount(),1);
//
//
//        condition.setSeqNo(null);
//        condition.setType(BusinessType.ETC);
//        condition.setPageNum(0);
//        condition.setPageSize(3);
//        QueryOrderItemResult result6 = orderService.queryOrderItem(condition);
//        assertEquals(result6.getList().size(),3);
//        assertEquals(result6.getPageCount(),4);
//
//    }


    @Test
    public void testCallBackETCInvoice()   throws Exception
    {
        Order orderRes = orderService.subscribe(order);

        EtcInvoice etc = new EtcInvoice();
        etc.setPhone("18680333333");
        etc.setInvoiceHeader("满易网科技有限公司");
        etc.setPostAddress("山东省济南市历下区奥体北路山东高速大厦 刘凯华收");
        etc.setAmount(new BigDecimal(1000));
        etc.setSeqNo(orderRes.getItemSeqs().get(1));
        etc.setOrderId(orderRes.getId());
        etc.setUserId(orderRes.getUserId());
        etc.setPayableAmount(new BigDecimal(100));

        etcService.createETCInvoiceOrder(etc);

        EtcResult callBack = new EtcResult();
        callBack.setOrderItemSeq(orderRes.getItemSeqs().get(1));
        callBack.setState("success");
        etcService.EtcInvoiceCallBack(callBack);

        QueryOrderItemCondition item = new QueryOrderItemCondition();
        item.setOrderId(orderRes.getId());
        List<OrderItem> itemlist =  orderDao.queryOrderItem(item);
        assertEquals(itemlist.size(),1);
        assertEquals(itemlist.get(0).getState(),OrderItemStatus.SUCCESS);

    }

}