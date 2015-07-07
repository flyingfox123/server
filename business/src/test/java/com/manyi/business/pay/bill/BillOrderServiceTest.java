package com.manyi.business.pay.bill;

import com.google.gson.Gson;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.order.OrderService;
import com.manyi.business.order.bean.BusinessType;
import com.manyi.business.order.bean.Order;
import com.manyi.business.order.bean.OrderBean;
import com.manyi.business.order.support.ETCBean;
import com.manyi.business.order.support.EtcInvoiceBean;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.account.support.AccountServiceImpl;
import com.manyi.business.pay.account.support.dao.AccountDao;
import com.manyi.business.pay.account.support.entity.Account;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.common.Constant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ZhangYuFeng on 2015/6/16 0016,16:18.
 * @Description:
 * @reviewer:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-bill-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class BillOrderServiceTest {
    @Autowired
    private BillOrderService billOrderService;

    @Autowired
    private OrderService orderService;

    Gson gson = new Gson();

    private static long USER= 111;

    @Autowired
    private AccountServiceImpl accountService;

    OrderBean order = new OrderBean();

    @Autowired
    private AccountDao accountDao;
    @Before
    public void setUp() throws Exception {

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
            etc.setETCCode("650010203010"+i);
            etc.setPlateNum("鲁A 12456" + i);
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


        AccountInfoBean accountInfoBean = new AccountInfoBean();

        Account account = new Account();
        account.setUserId(111);
        account.setBalance(new BigDecimal(0));
        account.setRealName("zhang");
        account.setIdCardNo("12344");
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        account.setType(Constant.DRIVER);
        account.setState(Constant.NORMAL);

        accountDao.saveAccount(account);
    }

    @Test
    @Rollback(true)
    public void createEtcTest() throws IOException {
        BillBean billBean = new BillBean();
        billBean.setOrderNo("201506182010726313");
        try {
            Order order1 = orderService.subscribe(order);
            billBean.setOrderNo(order1.getSeqNo());
            billOrderService.createEtcBill(billBean);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
