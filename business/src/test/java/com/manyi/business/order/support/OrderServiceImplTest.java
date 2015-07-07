package com.manyi.business.order.support;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.google.gson.Gson;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.etc.bean.*;
import com.manyi.business.etc.support.ETCServiceImpl;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.OrderServiceImpl;
import com.manyi.business.order.support.dao.OrderDao;
import com.manyi.business.pay.account.AccountService;
import com.manyi.business.pay.account.support.dao.AccountDao;
import com.manyi.business.pay.account.support.entity.AccBankCard;
import com.manyi.business.pay.account.support.entity.Account;
import com.manyi.business.pay.common.Constant;
import com.manyi.common.bean.response.ResponseBean;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-business-servlet.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class OrderServiceImplTest {


    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ETCServiceImpl etcService;


    @Autowired
    private  AccountDao accountDao;

    Gson gson = new Gson();

    private static long USER= 1314;

    @Autowired
    private OrderDao orderDao;

    OrderBean order = new OrderBean();


    public static final String SUCCESS = "success";
    @Before
    public void setUp() throws Exception {

        Account account = new Account();
        account.setUserId(USER);
        account.setBalance(new BigDecimal(0));
        account.setRealName("ddddd");
        account.setIdCardNo("370303030030303303");
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        account.setType(Constant.DRIVER);
        account.setState(Constant.NORMAL);
        accountDao.saveAccount(account);


        AccBankCard accBankCard = new AccBankCard();
        accBankCard.setAccountId(account.getAccountId());
        accBankCard.setCardNo("6259063249114799");
        accBankCard.setMobile("18688888888");
        accBankCard.setBankName("招商银行");
        accBankCard.setBankBranch("奥体分行");
        accBankCard.setBindState(Constant.HASBIND);
        accBankCard.setBankCode("ICBC");
        accountDao.saveAccBankCard(accBankCard);

        order.setUserId(USER);
        order.setDiscountAmount("0");
        order.setPaidAmount("300");
        order.setPayableAmount("300");
        order.setType(10);
        order.setItemNum(4);
        List<String> jsonList =new ArrayList<String>();
        for(int i = 0; i <3; i++)
        {
            ETCBean etc = new ETCBean();
            etc.setETCCode("123456789888");
            etc.setPlateNum("鲁鲁鲁鲁鲁A");
            etc.setPayableAmount("100");
            etc.setType(BusinessType.ETC);
            jsonList.add(gson.toJson(etc)) ;
        }

        EtcInvoiceBean etc = new EtcInvoiceBean();
        etc.setPhone("18680333333");
        etc.setInvoiceHeader("满易网科技有限公司");
        etc.setPostAddress("山东省济南市历下区奥体北路山东高速大厦 刘凯华收");
        etc.setAddressee("李四");
        etc.setAmount("300");
        etc.setPayableAmount("300");
        etc.setType(BusinessType.ETC_INVOICE);
        jsonList.add(gson.toJson(etc));
        order.setItemJsons(jsonList);
    }

    private   OrderBean createOrderReq(long userId)
    {
        OrderBean bean = new OrderBean();
        bean.setUserId(userId);
        bean.setDiscountAmount("0");
        bean.setPaidAmount("300");
        bean.setPayableAmount("300");
        bean.setType(10);
        bean.setItemNum(4);
        List<String> jsonList =new ArrayList<String>();
        for(int i = 0; i <1; i++)
        {
            ETCBean etc = new ETCBean();
            etc.setETCCode("111111111111");
            etc.setPlateNum("鲁A12345");
            etc.setPayableAmount("100");
            etc.setType(BusinessType.ETC);
            jsonList.add(gson.toJson(etc)) ;
        }

        EtcInvoiceBean etc = new EtcInvoiceBean();
        etc.setPhone("18680333333");
        etc.setInvoiceHeader("满易网科技有限公司");
        etc.setPostAddress("山东省济南市历下区奥体北路山东高速大厦 刘凯华收");
        etc.setAmount("300");
        etc.setAddressee("李四");
        etc.setPayableAmount("300");
        etc.setType(BusinessType.ETC_INVOICE);
        jsonList.add(gson.toJson(etc));
        bean.setItemJsons(jsonList);
        return  bean;
    }

    @Test
    public void testSubscribe() throws Exception {
        System.out.println(gson.toJson(order));
        Order orderRes = orderService.subscribe(order);
        Order order1 = orderDao.getOrderBySeq(orderRes.getSeqNo());
        assertNotNull(order1);
        assertEquals(300,order1.getPaidAmount().intValue());
        ResponseBean bean = new ResponseBean();
        bean.setBody(orderRes);
        bean.setState(SUCCESS);
        System.out.println(gson.toJson(bean));


    }

    @Test
    public void testSubscribeExceptionNull() {
        //空指针异常
        try
        {
           Order order1 =  orderService.subscribe(null);

        } catch (Exception e) {
            assertTrue(e instanceof BusinessException);
        }
    }


    @Test
    public void testCancelOrder() throws Exception {

         Order orderRes =  orderService.subscribe(order);

        orderService.cancelOrder(orderRes.getSeqNo());
        Order order1 = orderDao.getOrderBySeq(orderRes.getSeqNo());
        assertNotNull(order1);
        assertEquals(OrderStatus.CANCELED,order1.getState());
        List<OrderItem> list1 = orderDao.queryItemByOrder(orderRes.getId()) ;
        assertNotNull(list1);
        assertEquals(list1.size(),4);
        for (OrderItem item : list1)
        {
            assertEquals(OrderItemStatus.CANCELED,item.getState());
        }

    }

    @Test
    public void testPayForOrder() throws Exception {
       Order  orderRes = orderService.subscribe(order);
        orderService.orderPayCallBack(orderRes.getSeqNo(),"300",1,0);

        Order order2 = orderDao.getOrderBySeq(orderRes.getSeqNo());
        assertNotNull(order2);
        assertEquals(OrderStatus.PAYED,order2.getState());
        List<OrderItem> list2 = orderDao.queryItemByOrder(orderRes.getId()) ;
        assertNotNull(list2);
        assertEquals(list2.size(),4);
        for (OrderItem item : list2)
        {
            assertEquals(OrderItemStatus.PAYED,item.getState());
        }

    }
    @Test
    public void testQueryOrder() throws Exception {


//        Order order1 =  orderService.subscribe(createOrderReq( 101));
////
//        Order order2 = orderService.subscribe(createOrderReq( 102));
//
//        Order order3 =  orderService.subscribe(createOrderReq( 102));
//
//        Order order4 =    orderService.subscribe(createOrderReq( 102));
//        orderService.cancelOrder(order4.getSeqNo());
//
//        Order order5 =    orderService.subscribe(createOrderReq( 105));
//
//        Order order6 =  orderService.subscribe(createOrderReq( 106));
//        orderService.payForOrder(order6.getSeqNo(),new BigDecimal(300),1);
//
//
//        Order order7 =  orderService.subscribe(createOrderReq(107));
//        orderService.payForOrder(order7.getSeqNo(),new BigDecimal(300),1);
//
//        QueryOrderCondition condition = new  QueryOrderCondition();
//        List<Order>  list = orderService.queryOrder(condition);
//        condition.setPageNum(1);
//        condition.setPageSize(3);
//        List<Order>  list1 = orderService.queryOrder(condition);
//        assertEquals(list1.size(),3);
//        int count1 = orderService.queryOrderCount(condition) ;
//        condition.setState(OrderStatus.PENDING);
//        List<Order>  list2= orderService.queryOrder(condition);
//        assertEquals(list2.size(),3);
//
//        condition.setState(OrderStatus.PAYING);
//        List<Order>  list3  = orderService.queryOrder(condition);
//        assertEquals(list3.size(),2);
//
//        condition.setState(OrderStatus.PENDING);
//        condition.setUserId(102);
//        List<Order>  list4 = orderService.queryOrder(condition);
//        assertEquals(list4.size(),2);
//
//        condition.setState(OrderStatus.PENDING);
//        condition.setUserId(101);
//        condition.setSeqNo(order1.getSeqNo());
//        List<Order>  list5 = orderService.queryOrder(condition);
//        assertEquals(list5.size(),1);
//        int count5 = orderService.queryOrderCount(condition) ;
//        assertEquals(count5,1);
//       condition.setState(OrderStatus.PENDING);
//        condition.setUserId(103);
//        condition.setSeqNo(order1.getSeqNo());
//        List<Order>  list6 = orderService.queryOrder(condition);
//        assertEquals(list6.size(),0);
//        int count6 = orderService.queryOrderCount(condition) ;
//        assertEquals(count6,0);
//        condition.setUserId(0);
//        condition.setSeqNo(null);
//        condition.setType(10);
//        condition.setPageNum(3);
//        condition.setPageSize(3);
//        condition.setState(null);
//        List<Order>  list7  = orderService.queryOrder(condition);
//        assertEquals(list7.size(),1);
//        int count7 = orderService.queryOrderCount(condition) ;
//        assertEquals(count7,7);
//
//        ResponseBean bean = new ResponseBean();
//        bean.setBody(list2);
//        bean.setState(SUCCESS);
//        System.out.println(gson.toJson(bean));

    }

    @Test
    public void  testOrderCallBack() throws BusinessException, IOException

    {

        Order orderRes = orderService.subscribe(order);
        orderService.orderPayCallBack(orderRes.getSeqNo(),"300",PayChannel.XL_CHANNEL,0);
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
    public void  testETCPreCallBack() throws BusinessException, IOException, APIConnectionException, APIRequestException

    {

        Order orderRes = orderService.subscribe(order);
        orderService.orderPayCallBack(orderRes.getSeqNo(),"300",PayChannel.XL_CHANNEL,0);

        ETCPreChargeRequest request = new ETCPreChargeRequest() ;
        request.setOrderSeq(orderRes.getSeqNo());
        request.setState(0);
        List<EtcResult> backlist = new ArrayList<EtcResult>();
        for( OrderItem item : orderRes.getItemList())
        {
            EtcResult result = new EtcResult();
            result.setOrderItemSeq(item.getSeqNo());
            result.setState(0);
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
            if(info.equals(BusinessType.ETC))
            {
                assertEquals(OrderItemStatus.SUCCESS,info.getState());
            }

        }

    }



//    @Test
//    public void  testETCPreCallBack1() throws BusinessException, IOException, APIConnectionException, APIRequestException
//
//    {
//        String orderSeq = "201506111010989200";
//        ETCPreChargeRequest request = new ETCPreChargeRequest() ;
//        request.setOrderSeq(orderSeq);
//        request.setState(0);
//        Order o1 = orderDao.getOrderBySeq(orderSeq);
//        orderService.orderPayCallBack(orderSeq,"3000",PayChannel.XL_CHANNEL);
//        List<OrderItem> list = orderDao.queryItemByOrder(o1.getId());
//        List<EtcResult> backlist = new ArrayList<EtcResult>();
//        for( OrderItem item : list)
//        {
//            EtcResult result = new EtcResult();
//            result.setOrderItemSeq(item.getSeqNo());
//            result.setState(0);
//            backlist.add(result);
//        }
//
//        request.setList(backlist);
//        etcService.preChargeETCCallBack(request);
//
//
//    }



    @Test
    public void  testETCPreCallBackFailed() throws BusinessException, IOException, APIConnectionException, APIRequestException

    {

        Order orderRes = orderService.subscribe(order);
        orderService.orderPayCallBack(orderRes.getSeqNo(),"300",PayChannel.XL_CHANNEL,0);

        ETCPreChargeRequest request = new ETCPreChargeRequest() ;
        request.setOrderSeq(orderRes.getSeqNo());
        request.setState(0);
        List<EtcResult> backlist = new ArrayList<EtcResult>();
        for( OrderItem item : orderRes.getItemList())
        {
            EtcResult result = new EtcResult();
            result.setOrderItemSeq(item.getSeqNo());
            result.setState(0);
            backlist.add(result);
        }
        backlist.get(0).setState(1);
        request.setList(backlist);
        etcService.preChargeETCCallBack(request);

        Order o1 = orderDao.getOrderById(orderRes.getId());
        QueryOrderItemCondition item = new QueryOrderItemCondition();
        item.setOrderId(orderRes.getId());
        List<OrderItem> itemlist =  orderDao.queryOrderItem(item);
        assertEquals(itemlist.size(),4);
        assertEquals(OrderStatus.PAYED,o1.getState());

    }

    @Test
    public void testQueryOrderItem() throws Exception {
//
        Order orderRes =  orderService.subscribe(order);
        QueryOrderItemCondition condition = new QueryOrderItemCondition();
       // condition.setOrderId(orderRes.getId());
        condition.setOrderSeq(orderRes.getSeqNo());
        List<OrderItem> list = orderService.queryOrderItem(condition) ;
        assertEquals(list.size(),4);
        condition.setPageSize(2);
        condition.setPageNum(0);
        List<OrderItem> list1 = orderService.queryOrderItem(condition) ;
        assertEquals(list1.size(),2);
        condition.setType("sksk");
        List<OrderItem> list2 = orderService.queryOrderItem(condition) ;
        assertEquals(list2.size(),0);

        ResponseBean bean = new ResponseBean();
        bean.setBody(list);
        bean.setState(SUCCESS);
        System.out.println(gson.toJson(bean));

//
//        QueryOrderItemCondition condition1 = new QueryOrderItemCondition();
//        // condition.setOrderId(orderRes.getId());
//        condition1.setOrderSeq("201506091010312184");
//        condition1.setUserId(67);
//        condition1.setType("10");
//        condition1.setState("canceled");
//        List<OrderItem> list2 = orderService.queryOrderItem(condition1) ;
    }


    @Test
    public void testCallBackETCInvoice()   throws Exception
    {
        OrderBean o = new OrderBean();
        o.setUserId(101);
        o.setDiscountAmount("0");
        o.setPaidAmount("300");
        o.setPayableAmount("300");
        o.setType(10);
        List<String> jsonList =new ArrayList<String>();
        EtcInvoiceBean etc = new EtcInvoiceBean();
        etc.setPhone("18680333333");
        etc.setInvoiceHeader("满易网科技有限公司");
        etc.setPostAddress("山东省济南市历下区奥体北路山东高速大厦 刘凯华收");
        etc.setAmount("300");
        etc.setPayableAmount("0");
        etc.setType(BusinessType.ETC_INVOICE);
        etc.setAddressee("李四");
        jsonList.add(gson.toJson(etc));
        o.setItemJsons(jsonList);

        Order orderRes = orderService.subscribe(o);

        EtcResult callBack = new EtcResult();
        callBack.setOrderItemSeq(orderRes.getItemList().get(0).getSeqNo());
        callBack.setState(0);
        etcService.EtcInvoiceCallBack(callBack);

        QueryOrderItemCondition item = new QueryOrderItemCondition();
        item.setOrderId(orderRes.getId());
        List<OrderItem> itemlist =  orderDao.queryOrderItem(item);
        assertEquals(itemlist.size(),1);
        assertEquals(itemlist.get(0).getState(),OrderItemStatus.SUCCESS);

    }


    @Test
    public void testETCPayCallBack()   throws Exception
    {
        OrderBean o = new OrderBean();
        o.setUserId(USER);
        o.setDiscountAmount("0");
        o.setPaidAmount("300");
        o.setPayableAmount("300");
        o.setType(10);
        List<String> jsonList =new ArrayList<String>();
        EtcInvoiceBean etc = new EtcInvoiceBean();
        etc.setPhone("18680333333");
        etc.setInvoiceHeader("满易网科技有限公司");
        etc.setPostAddress("山东省济南市历下区奥体北路山东高速大厦 刘凯华收");
        etc.setAmount("300");
        etc.setPayableAmount("0");
        etc.setType(BusinessType.ETC_INVOICE);
        etc.setAddressee("李四");
        jsonList.add(gson.toJson(etc));
        o.setItemJsons(jsonList);

        Order orderRes = orderService.subscribe(o);

        EtcResult callBack = new EtcResult();
        callBack.setOrderItemSeq(orderRes.getItemList().get(0).getSeqNo());
        callBack.setState(0);
        etcService.etcPayCallBack(orderRes.getSeqNo(), "300", PayChannel.BANK_CHANNEL, 0);

        QueryOrderItemCondition item = new QueryOrderItemCondition();
        item.setOrderId(orderRes.getId());
        List<OrderItem> itemlist =  orderDao.queryOrderItem(item);
        assertEquals(itemlist.size(),1);
        assertEquals(itemlist.get(0).getState(),OrderStatus.PAYED);

    }



    @Test
    public  void  testQueryEtcOrderAmount() throws IOException, BusinessException, APIConnectionException, APIRequestException {

        Order order1 =  orderService.subscribe(createOrderReq( USER));

        Order order4 =    orderService.subscribe(createOrderReq( USER));
        orderService.cancelOrder(order4.getSeqNo());

        Order order6 =  orderService.subscribe(createOrderReq( USER));
        orderService.payForOrder(order6.getSeqNo(),new BigDecimal(300),1);


        Order orderRes = orderService.subscribe(order);
        orderService.orderPayCallBack(orderRes.getSeqNo(),"300",PayChannel.XL_CHANNEL,0);

        ETCPreChargeRequest request = new ETCPreChargeRequest() ;
        request.setOrderSeq(orderRes.getSeqNo());
        request.setState(0);
        List<EtcResult> backlist = new ArrayList<EtcResult>();
        for( OrderItem item : orderRes.getItemList())
        {
            EtcResult result = new EtcResult();
            result.setOrderItemSeq(item.getSeqNo());
            result.setState(0);
            backlist.add(result);
        }

        request.setList(backlist);
        etcService.preChargeETCCallBack(request);

        Order order7 =  orderService.subscribe(createOrderReq( USER));
        orderService.orderPayCallBack(order7.getSeqNo(),"300",PayChannel.XL_CHANNEL,0);

        EtcOrderAmountResult result =  etcService.queryEtcOrderAmount(USER);

        assertEquals((int)result.getMonthAmount(),600);
        assertEquals((int)result.getYearAmount(),600);

        result.setPlateNum("鲁A 12321");
        result.setETCCode("600102020202");

        ResponseBean response = new ResponseBean();
        response.setBody(result);
        response.setState("success");
        System.out.println(gson.toJson(response));
    }


    @Test
    public  void  testQueryUnfinishOrder() throws IOException, BusinessException, APIConnectionException, APIRequestException {

        Order order1 =  orderService.subscribe(createOrderReq( USER));
        Order order2 =  orderService.subscribe(createOrderReq( USER));
        QueryOrderCondition condition = new  QueryOrderCondition();
        condition.setUserId(USER);
        List<Order>  list1 = orderService.queryOrder(condition);
        assertEquals(list1.size(),2);
        List<Order>  list = orderService.queryUnfinishedOrder(condition);
        assertEquals(list.size(),2);


        Order orderRes = orderService.subscribe(order);
        orderService.orderPayCallBack(orderRes.getSeqNo(),"300",PayChannel.XL_CHANNEL,0);

        ETCPreChargeRequest request = new ETCPreChargeRequest() ;
        request.setOrderSeq(orderRes.getSeqNo());
        request.setState(0);
        List<EtcResult> backlist = new ArrayList<EtcResult>();
        for( OrderItem item : orderRes.getItemList())
        {
            EtcResult result = new EtcResult();
            result.setOrderItemSeq(item.getSeqNo());
            result.setState(0);
            backlist.add(result);
        }

        request.setList(backlist);
        etcService.preChargeETCCallBack(request);

        QueryOrderCondition condition1 = new  QueryOrderCondition();
        condition1.setUserId(USER);
        List<Order>  list11 = orderService.queryOrder(condition1);
        assertEquals(list11.size(),3);
        List<Order>  list12 = orderService.queryUnfinishedOrder(condition1);
        assertEquals(list12.size(),2);

        orderService.cancelOrder(order1.getSeqNo());


        QueryOrderCondition condition2 = new  QueryOrderCondition();
        condition2.setUserId(USER);
        List<Order>  list22 = orderService.queryOrder(condition2);
        assertEquals(list22.size(),3);
        List<Order>  list23 = orderService.queryUnfinishedOrder(condition2);
        assertEquals(list23.size(),1);

    }


}