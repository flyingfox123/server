package com.manyi.business.pay.bill;

import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.BillServiceImpl;
import com.manyi.business.pay.bill.support.entity.Bill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author ZhangYuFeng on 2015/6/19 0019,15:01.
 * @Description:
 * @reviewer:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-bill-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class BillForManageServiceTest {

    @Autowired
    private BillForManageService billForManageService;

    @Autowired
    private BillServiceImpl billService;

    @Test
    @Rollback(true)
    public void findBillTest() {
        BillBean billBean = new BillBean();
        billBean.setAccountId(16);
        billBean.setAmount(new BigDecimal("1000"));
        billBean.setBusinessType(1);
        billBean.setChannelBillNo("123456789");
        billBean.setCheckState(1);
        billBean.setTradeType(1);
        billBean.setSourceId(1);
        billBean.setOrderId(000001);
        billBean.setTradeState(1);
        billService.createBill(billBean);


        BillBean billBean1 = new BillBean();
        billBean1.setTradeState(1);
        billBean1.setCheckState(1);
        billBean1.setMinAmount(new BigDecimal(20));
        billBean1.setMaxAmount(new BigDecimal(20000));
        billBean1.setTradeType(1);
//        billBean1.setTradeTimeStart("2015-06-01");
//        billBean1.setTradeTimeEnd("2015-06-19");
//        billBean1.setTradeTimeStart("2015-06-01");
//        billBean1.setTradeTimeEnd("2015-06-19");
        List<Bill> billList = billForManageService.findBill(billBean1);
        assertTrue(billList.size()>0);
        System.out.println(billList.size());
    }

    @Test
    public void getBillCountTest(){
        BillBean billBean = new BillBean();
        billBean.setPageSize(5);

        int count = billForManageService.getBillCount(billBean);

        assertTrue(count>0);
    }
}
