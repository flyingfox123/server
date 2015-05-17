package com.manyi.business.carriersign.support;

import com.manyi.business.carriersign.bean.CarrierQualificationBean;
import com.manyi.business.carriersign.exception.BusinessCarrierException;
import com.manyi.business.carriersign.support.CarrierQualificationServiceImpl;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * Created by zhangyufeng on 2015/4/12 0012.
 */
public class CarrierQulificationServiceImplTest extends TestCase{

    @Autowired
    private CarrierQualificationServiceImpl carrierQulificationService;

    @Before
    public void init() {
        ApplicationContext aCtx = new FileSystemXmlApplicationContext(
                "classpath*:spring-business-servlet.xml");
        this.carrierQulificationService = (CarrierQualificationServiceImpl) aCtx.
                getBean("carrierQualificationService");
    }




    @Test
    public void testAddQualification() throws Exception{
        init();
        // 模拟提交操作
        CarrierQualificationBean carrierQualificationBean = new CarrierQualificationBean();
        carrierQualificationBean.setUserName("zhangsan");
        carrierQualificationBean.setReviewState("0");
        //carrierQulificationService.createQulification(carrierQualificationBean);
    }

    @Test
    public void testFileSave() throws BusinessCarrierException{
        init();
        String fileUrl="upload\\";
        String fileName="1.jpg";
        carrierQulificationService.fileSave(fileUrl,fileName);
    }


}
