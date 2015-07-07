package com.manyi.business.highwaytoll.support;

import com.manyi.business.highwaytoll.bean.HighwayResponseBean;
import com.manyi.business.highwaytoll.bean.HighwayTollBean;
import com.manyi.business.highwaytoll.bean.HistoryRouteBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-business-servlet-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class HighwayTollServiceImplTest {

    @Autowired
    public HighwayTollServiceImpl highwayTollServiceImpl;

    @Test
    public void testSaveHistoryRoute() throws Exception {
        HistoryRouteBean historyRouteBean = new HistoryRouteBean();
        historyRouteBean.setUserId(13L);
        historyRouteBean.setBeginRoute("千佛山");
        historyRouteBean.setEndRoute("故宫");
        historyRouteBean.setStartCity("济南");
        historyRouteBean.setEndCity("北京");
        historyRouteBean.setStartX(10.89899);
        historyRouteBean.setStartY(123.86334);
        historyRouteBean.setEndX(90.89343);
        historyRouteBean.setEndY(43.727384);
        highwayTollServiceImpl.saveHistoryRoute(historyRouteBean );
        List<HistoryRouteBean> historyRouteBeans = highwayTollServiceImpl.getHistoryRoute(13L);
        assertNotNull(historyRouteBeans);

    }

    @Test
    public void testGetHistoryRoute() throws Exception {
        HistoryRouteBean historyRouteBean = new HistoryRouteBean();
        historyRouteBean.setUserId(13L);
        historyRouteBean.setBeginRoute("千佛山");
        historyRouteBean.setEndRoute("大明湖");
        historyRouteBean.setStartCity("济南");
        historyRouteBean.setEndCity("北京");
        highwayTollServiceImpl.saveHistoryRoute(historyRouteBean );
        List<HistoryRouteBean> historyRouteBeans = highwayTollServiceImpl.getHistoryRoute(13L);
        assertNotNull(historyRouteBeans);
    }

    @Test
    public void testDeleteHistoryRoute() throws Exception {
        HistoryRouteBean historyRouteBean = new HistoryRouteBean();
        historyRouteBean.setUserId(100);
        historyRouteBean.setBeginRoute("千佛山");
        historyRouteBean.setEndRoute("大明湖");
        historyRouteBean.setStartCity("济南");
        historyRouteBean.setEndCity("北京");
        highwayTollServiceImpl.saveHistoryRoute(historyRouteBean);
        List<HistoryRouteBean> historyRouteBeans = highwayTollServiceImpl.getHistoryRoute(100);
        assertNotEquals(0, historyRouteBeans.size());
        highwayTollServiceImpl.deleteHistoryRoute(100);
        List<HistoryRouteBean> historyRouteBeans1 = highwayTollServiceImpl.getHistoryRoute(100);
        assertEquals(0, historyRouteBeans1.size());
    }

    @Test
    public void testgetTotalFee() throws Exception {
        HighwayTollBean highwayTollBean= new HighwayTollBean();
        highwayTollBean.setAxisNum(2);
        highwayTollBean.setBeginX("117.1201922617");
        highwayTollBean.setBeginY("36.6564049115");
        highwayTollBean.setEndX("116.419136");
        highwayTollBean.setEndY("40.104305");
        highwayTollBean.setOilWear(10);
        highwayTollBean.setUnitPrice(6);
        highwayTollBean.setTotalWeight(4);
        HighwayResponseBean highwayResponseBean = highwayTollServiceImpl.getTotalFee(highwayTollBean);
        assertNotNull(highwayResponseBean);
    }
}