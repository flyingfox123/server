package com.manyi.business.pay.bill;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.BillServiceImpl;
import com.manyi.business.pay.bill.support.entity.Bill;
import com.manyi.business.pay.common.constant.BillEnum;
import com.manyi.business.pay.common.constant.TradeState;
import com.manyi.business.pay.common.constant.TradeType;
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

import static org.junit.Assert.*;
/**
 * Created by zhangyufeng on 2015/6/5 0005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-bill-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class BillServiceTest {

    @Autowired
    BillServiceImpl billService;

    @Test
    @Rollback(true)
    public void createBillTest(){
        BillBean billBean = new BillBean();
        billBean.setAccountId(16);
        billBean.setAmount(new BigDecimal("10000"));
        billBean.setBusinessType(1);
        billBean.setChannelBillNo("123456789");
        billBean.setCheckState(1);
        billBean.setSourceId(1);
        billBean.setOrderId(000001);
        billBean.setTradeState(1);
        billService.createBill(billBean);

        List<Bill> billList = billService.findBill(billBean);
        Bill bill = billList.get(0);
        assertEquals("123456789",bill.getChannelBillNo());
    }

    @Test
    @Rollback(true)
    public void findBillTest(){
        BillBean billBean = new BillBean();
        billBean.setAccountId(16);
        billBean.setAmount(new BigDecimal("10000"));
        billBean.setBusinessType(1);
        billBean.setChannelBillNo("223456789");
        billBean.setCheckState(1);
        billBean.setSourceId(1);
        billBean.setOrderId(000001);
        billBean.setTradeState(1);
        billService.createBill(billBean);

        List<Bill> billList = billService.findBill(billBean);
        Bill bill = billList.get(0);
        assertEquals("223456789",bill.getChannelBillNo());
    }


    @Test
    @Rollback(true)
    public void updateBillTest(){
        BillBean billBean = new BillBean();
        billBean.setAccountId(16);
        billBean.setAmount(new BigDecimal("10000"));
        billBean.setBusinessType(1);
        billBean.setChannelBillNo("123456788");
        billBean.setCheckState(1);
        billBean.setSourceId(1);
        billBean.setOrderId(000002);
        billBean.setTradeState(1);
        billService.createBill(billBean);

        BillBean billBean1 = new BillBean();
        billBean1.setAccountId(16);
        billBean1.setTradeState(2);
        billBean1.setBillNo(billBean.getBillNo());
        billService.updateBill(billBean1);

        List<Bill> billList = billService.findBill(billBean);
        Bill bill = billList.get(0);
        assertEquals(2,bill.getTradeState());
    }

    @Test
    @Rollback(true)
    public void findUnDealBill(){
        BillBean billBean = new BillBean();
        billBean.setAccountId(16);
        billBean.setBillNo("150605000002");
        billBean.setAmount(new BigDecimal("10000"));
        billBean.setBusinessType(1);
        billBean.setChannelBillNo("123456788");
        billBean.setCheckState(1);
        billBean.setSourceId(1);
        billBean.setOrderId(000002);
        billBean.setTradeState(0);
        billService.createBill(billBean);

        List<Bill> billList = billService.findUnDealBill();
        assertTrue(billList.size()>0);
    }

    @Test
    public void findRebundBill() throws BusinessException {
        Bill bill = billService.findBill("201506182010726313", BillEnum.MONEYGUR, TradeType.PAYMENT, TradeState.PAYSUCCESS);
        System.out.println(bill);
    }


}
