package com.manyi.business.pay.log;

import com.manyi.business.pay.log.bean.BillInterLogBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
/**
 * @author ZhangYuFeng
 * @Description:
 * @version: 1.0.0 on 2015/7/1 0001,10:19.
 * @reviewer:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-bill-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @Test
    @Rollback(true)
    public void addRequestStrTest(){
        BillInterLogBean billInterLogBean = new BillInterLogBean();
        billInterLogBean.setReqMessage("request test");
        logService.addRequestStr(billInterLogBean);
        billInterLogBean.setRespMessage("response test");
        logService.addResponseStr(billInterLogBean);
    }
}
