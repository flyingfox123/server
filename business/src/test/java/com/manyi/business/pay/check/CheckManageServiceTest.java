package com.manyi.business.pay.check;

import com.manyi.business.pay.check.bean.CheckDetailBean;
import com.manyi.business.pay.check.support.entity.CheckDetail;
import com.manyi.business.pay.check.support.entity.CheckSum;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/26 0026,8:48.
 * @Description:
 * @reviewer:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-bill-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class CheckManageServiceTest {

    @Autowired
    private CheckManageService checkManageService;

    @Test
    public void getCheckSumTest(){
        CheckDetailBean checkDetailBean = new CheckDetailBean();
        List<CheckSum> list = checkManageService.getCheckSum(checkDetailBean);
        assertTrue(list.size()>=0);
    }

    @Test
    public void getCheckDetailTest(){
        CheckDetailBean checkDetailBean = new CheckDetailBean();
        List<CheckDetail> checkDetailList = checkManageService.getCheckDetail(checkDetailBean);
        assertTrue(checkDetailList.size()>=0);
    }

}
