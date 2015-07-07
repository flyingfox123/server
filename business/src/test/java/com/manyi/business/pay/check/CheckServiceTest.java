package com.manyi.business.pay.check;

import com.manyi.base.exception.BusinessException;
import com.manyi.common.util.OrderSeqGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangYuFeng on 2015/6/11 0011,10:33.
 * @Description:
 * @reviewer:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-bill-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class CheckServiceTest {

    @Autowired
    @Qualifier("dealCheckFileSeviceTestImpl")
    CheckService checkService;

    @Test
    @Rollback(true)
    public void getCheckFileUrlTest(){
        checkService.dealCheckFile();
    }

}
