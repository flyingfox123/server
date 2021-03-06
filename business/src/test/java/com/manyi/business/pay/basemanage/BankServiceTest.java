package com.manyi.business.pay.basemanage;

import com.manyi.business.pay.basemanage.support.entity.BankInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
/**
 * @author ZhangYuFeng
 * @Description:
 * @version: 1.0.0 on 2015/7/1 0001,9:46.
 * @reviewer:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-bill-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class BankServiceTest {
    @Autowired
    private BankService bankService;

    @Test
    public void getBakInfoAllTest(){
        List<BankInfo> bankInfoList = bankService.getBakInfoAll();
        System.out.println(bankInfoList.size());
        assertTrue(bankInfoList.size()>0);
    }

}
