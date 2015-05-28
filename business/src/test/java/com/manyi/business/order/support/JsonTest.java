package com.manyi.business.order.support;

import com.google.gson.Gson;
import com.manyi.base.entity.Message;
import com.manyi.business.etc.bean.ETCPreChargeRequest;
import com.manyi.business.etc.bean.Etc;
import com.manyi.business.etc.bean.EtcInvoice;
import com.manyi.business.order.bean.Order;
import com.manyi.business.order.bean.OrderItem;
import com.manyi.business.order.bean.QueryOrderItemResult;
import com.manyi.business.order.bean.QueryOrderResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-business-servlet.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class JsonTest {

    @Test
    public void testJsonObj()
    {
        Order order = new Order();
        order.setUserId(100);
        order.setPaidAmount(new BigDecimal(1000));
        order.setPayableAmount(new BigDecimal(1000));
        order.setDiscountAmount(new BigDecimal(0));
        order.setItemNum(4);
        order.setSeqNo("ORDER-20150513-222500-00001");
        order.setId(10);
        List<String> list = new ArrayList<String>();
        for(int i=0 ; i<4 ;i++)
        {

            list.add("ORDERITEM-20150513-222500-000"+i);
        }
        order.setItemSeqs(list);
        Message message = new Message();
        message.setCodeMsg(200);
        message.setErrorMsg("success");
        message.setResponse(order);
        Gson gson = new Gson();
        System.out.println(gson.toJson(message));

    }

    @Test
    public void testQueryOrderJson()
    {
        QueryOrderResult result = new QueryOrderResult();
        result.setPageNum(1);
        result.setPageSize(5);
        result.setPageCount(1);
        List<Order> list = new ArrayList<Order>();
        for(int i=0 ; i<4 ;i++)
        {
            Order order = new Order();
            order.setUserId(100);
            order.setPaidAmount(new BigDecimal(1000));
            order.setPayableAmount(new BigDecimal(1000));
            order.setDiscountAmount(new BigDecimal(0));
            order.setItemNum(4);
            order.setSeqNo("ORDER-20150513-222500-0000" + i);
            order.setId(10);
            order.setState("pending");
            order.setCreateTime(new Date(System.currentTimeMillis()));
            order.setDescription("ETC预充值");
            list.add(order);
        }
        result.setList(list);

        Gson gson = new Gson();
        System.out.println(gson.toJson(result));

        }


    @Test
    public void testQueryOrderItemJson()
    {
        Gson gson = new Gson();
        QueryOrderItemResult result = new QueryOrderItemResult();
        result.setPageNum(1);
        result.setPageSize(5);
        result.setPageCount(1);


        List<OrderItem> list = new ArrayList<OrderItem>();
        for(int i=0 ; i<4 ;i++)
        {
            Etc etc = new Etc();
            etc.setId(i +10);
            etc.setETCCode("622023103030" + i);
            etc.setPlateNum("鲁A 12345");

            OrderItem orderItem = new OrderItem();
            orderItem.setUserId(100);
            orderItem.setPayableAmount(new BigDecimal(1000));
            orderItem.setSeqNo("ORDERITEM-20150513-222500-000"+i);
            orderItem.setId(i+100);
            orderItem.setState("pending");
            orderItem.setOrderId(50+i);
            orderItem.setType("etc");
            orderItem.setDescription(gson.toJson(etc));
           // orderItem.setCreateTime("");
            list.add(orderItem);
        }
        result.setList(list);


        System.out.println(gson.toJson(result));

    }

    @Test
    public void etcJsonTest()
    {
        Etc etc = new Etc();
        etc.setETCCode("622023103030" );
        etc.setPlateNum("鲁A 12345");
        etc.setPayableAmount(new BigDecimal(1000));
        etc.setSeqNo("ORDERITEM-20150513-222500-0001");
        etc.setOrderId(101);
        etc.setUserId(100);
        Gson gson = new Gson();
        System.out.println(gson.toJson(etc));

    }


    @Test
    public void etcInvoiceJsonTest()
    {
        EtcInvoice etc = new EtcInvoice();
        etc.setInvoiceHeader("满易网科技有限公司");
        etc.setAmount(new BigDecimal(10000));
        etc.setPostAddress("山东省济南市奥体北路山东高速 XXX收 ");
        etc.setPhone("18666666666");
        etc.setPayableAmount(new BigDecimal(1000));

        etc.setSeqNo("ORDERITEM-20150513-222500-0001");
        etc.setOrderId(101);
        etc.setUserId(100);
        Gson gson = new Gson();
        System.out.println(gson.toJson(etc));

    }

//
//    @Test
//    public void etcInvoiceJsonTest1()
//    {
//        ETCPreChargeRequest request = new ETCPreChargeRequest() ;
//
//        request.setState("failed");
//        request.setOrderSeq("ORDER-20150513-222500-00001");
//
//        List<InvoiceResult> list = new ArrayList<InvoiceResult>();
//
//
//        for(int i=0 ; i<2 ;i++)
//        {
//            InvoiceResult   result = new InvoiceResult();
//             result.setState("success");
//            result.setOrderItemSeq("ORDERITEM-20150513-222500-000"+i);
//            list.add(result);
//        }
//        InvoiceResult   result = new InvoiceResult();
//        result.setState("failed");
//        result.setOrderItemSeq("ORDERITEM-20150513-222500-000" + 3);
//        list.add(result);
//        request.setList(list);
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(request));
//
//    }


}
