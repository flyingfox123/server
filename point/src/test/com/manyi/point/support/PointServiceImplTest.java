package com.manyi.point.support;

import com.manyi.base.entity.State;
import com.manyi.base.entity.Type;
import com.manyi.point.bean.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-point-servlet.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class PointServiceImplTest extends TestCase {

    @Autowired
    private PointServiceImpl pointService;

    public static String SERVICEID = "MY0000";
    public static int USERID = 1;
    public static String EVENT_LOGIN = "300000";
    public static String EVENT_RECOMMEND = "300001";
    public static String EVENT_REGISTER = "300002";
    public static String EVENT_USE = "000000";
    public static String EVENT_UPDATE = "0000001";
    public static String EVENT_UPDATE_GROWTH = "0000002";

    public static String OP_USERID = "test";


    @Before
    public void setUp() throws Exception {

//        ApplicationContext aCtx = new FileSystemXmlApplicationContext(
//                "classpath:spring-point-servlet.xml");
//        this.pointService = (PointServiceImpl) aCtx
//                .getBean("pointService");
//          pointService.deleteTestData();
//
//        String test =    Type.REQUEST_AUTH.getErrorCode();
//        System.out.println(Type.REQUEST_AUTH.getErrorCode());
    }

    @After
    public void tearDown() throws Exception {

    //    pointService.deleteAllUserPoint(SERVICEID , USERID);

    }

    @Test
    public void testAddPointAndGrow() throws Exception {
        PointParam request = new PointParam(USERID,SERVICEID,EVENT_LOGIN,OP_USERID);
        pointService.addPointAndGrow(request);
        Point p =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p);
        assertEquals(1,p.getPoint());
        assertEquals(2,p.getGrowth());
        PointParam requestRecommend = new PointParam(USERID,SERVICEID,EVENT_RECOMMEND,OP_USERID);
        pointService.addPointAndGrow(requestRecommend);
        Point p1 =  pointService. queryUserPoint(SERVICEID , USERID);

        assertNotNull(p1);
        assertEquals(6,p1.getPoint());
        assertEquals(8,p1.getGrowth());
        PointParam requestRegister = new PointParam(USERID,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister);
        Point p2 =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p2);
        assertEquals(16,p2.getPoint());
        assertEquals(19,p2.getGrowth());
        QueryPointLogCondition condition  = new QueryPointLogCondition();
        condition.setUserId(USERID);
        PointLogResult response =  pointService.queryUserGrowthLog(condition);
        assertEquals(3,response.getList().size());
        PointLogResult response1 = pointService.queryUserPointLog(condition);
        assertEquals(3,response1.getList().size());

    }

    @Test
    public void testAddPoint() throws Exception {

        PointParam request = new PointParam(USERID,SERVICEID,EVENT_LOGIN,OP_USERID);
        pointService.addPoint(request);
        Point p =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p);
        assertEquals(1,p.getPoint());
        assertEquals(0,p.getGrowth());
        assertEquals("",0,p.getGrowth()) ;
        PointParam requestRecommend = new PointParam(USERID,SERVICEID,EVENT_RECOMMEND,OP_USERID);

        pointService.addPoint(requestRecommend);
        Point p1 =  pointService. queryUserPoint(SERVICEID , USERID);

        assertNotNull(p1);
        assertEquals(6,p1.getPoint());
        assertEquals(0,p1.getGrowth());
        PointParam requestRegister = new PointParam(USERID,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPoint(requestRegister);
        Point p2 =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p2);
        assertEquals(16,p2.getPoint());
        assertEquals(0,p2.getGrowth());
        QueryPointLogCondition condition  = new QueryPointLogCondition();
        condition.setUserId(USERID);
        PointLogResult response =  pointService.queryUserGrowthLog(condition);
        assertEquals(0,response.getList().size());
        PointLogResult response1 = pointService.queryUserPointLog(condition);
        assertEquals(3,response1.getList().size());

    }

    @Test
    public void testAddGrowth() throws Exception {
        PointParam request = new PointParam(USERID,SERVICEID,EVENT_LOGIN,OP_USERID);

        pointService.addGrowth(request);
        Point p =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p);
        assertEquals(0,p.getPoint());
        assertEquals(2,p.getGrowth());
        PointParam requestRecommend = new PointParam(USERID,SERVICEID,EVENT_RECOMMEND,OP_USERID);

        pointService.addGrowth(requestRecommend);

        Point p1 =  pointService. queryUserPoint(SERVICEID , USERID);

        assertNotNull(p1);
        assertEquals(0,p1.getPoint());
        assertEquals(8,p1.getGrowth());
        PointParam requestRegister = new PointParam(USERID,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addGrowth(requestRegister);
        Point p2 =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p2);
        assertEquals(0,p2.getPoint());
        assertEquals(19,p2.getGrowth());
        QueryPointLogCondition condition  = new QueryPointLogCondition();
        condition.setUserId(USERID);
        PointLogResult response =  pointService.queryUserGrowthLog(condition);
        assertEquals(3,response.getList().size());
        PointLogResult response1 = pointService.queryUserPointLog(condition);
        assertEquals(0,response1.getList().size());
    }

    @Test
    public void testUsePoint() throws Exception {
        PointParam requestRegister = new PointParam(USERID,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister);
        Point p =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p);
        assertEquals(10,p.getPoint());
        assertEquals(11,p.getGrowth());
        UsePointParam useRequest = new UsePointParam(USERID,SERVICEID,OP_USERID,5);

        pointService.usePoint(useRequest);
        Point p1 =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p1);
        assertEquals(5,p1.getPoint());
        assertEquals(11,p1.getGrowth());

        QueryPointLogCondition condition  = new QueryPointLogCondition();
        condition.setUserId(USERID);
        PointLogResult response =  pointService.queryUserGrowthLog(condition);
        assertEquals(1,response.getList().size());
        PointLogResult response1 = pointService.queryUserPointLog(condition);
        assertEquals(2,response1.getList().size());
        condition.setEventCode(EVENT_USE);
        PointLogResult response2 = pointService.queryUserPointLog(condition);
         assertEquals(1,response2.getList().size());
         assertEquals(-5,response2.getList().get(0).getValue());


    }

    @Test
    public void testUpdatePoint() throws Exception {

        PointParam requestRegister = new PointParam(USERID,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister);
        Point p =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p);
        assertEquals(10,p.getPoint());
        assertEquals(11,p.getGrowth());
        UpdatePointParam request = new UpdatePointParam(USERID,SERVICEID,OP_USERID,15);

        pointService.updatePoint( request) ;

        Point p1 =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p1);
        assertEquals(15,p1.getPoint());
        assertEquals(11,p1.getGrowth());

        QueryPointLogCondition condition  = new QueryPointLogCondition();
        condition.setUserId(USERID);
        PointLogResult response =  pointService.queryUserGrowthLog(condition);
        assertEquals(1,response.getList().size());
        PointLogResult response1 = pointService.queryUserPointLog(condition);
        assertEquals(2,response1.getList().size());
        condition.setEventCode(EVENT_UPDATE);
        PointLogResult response2 = pointService.queryUserPointLog(condition);
        assertEquals(1,response2.getList().size());
        assertEquals(5,response2.getList().get(0).getValue());
    }

    @Test
    public void testUpdateGrowth() throws Exception {

        PointParam requestRegister = new PointParam(USERID,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister);
        Point p =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p);
        assertEquals(10,p.getPoint());
        assertEquals(11,p.getGrowth());
        UpdateGrowthParam request = new UpdateGrowthParam(USERID,SERVICEID,OP_USERID,15);

        pointService.updateGrowth(request) ;

        Point p1 =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p1);
        assertEquals(10,p1.getPoint());
        assertEquals(15,p1.getGrowth());

        QueryPointLogCondition condition  = new QueryPointLogCondition();
        condition.setUserId(USERID);
        PointLogResult response =  pointService.queryUserGrowthLog(condition);
        assertEquals(2,response.getList().size());
        PointLogResult response1 = pointService.queryUserPointLog(condition);
        assertEquals(1,response1.getList().size());
        condition.setEventCode(EVENT_UPDATE_GROWTH);
        PointLogResult response2 = pointService.queryUserGrowthLog(condition);
        assertEquals(1,response2.getList().size());
        assertEquals(4,response2.getList().get(0).getValue());
    }

    @Test
    public void testQueryUserPoint() throws Exception {

        PointParam requestRegister = new PointParam(USERID,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister);
        Point p =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p);
        assertEquals(10,p.getPoint());
        assertEquals(11,p.getGrowth());
    }

    @Test
    public void testQueryPoints() throws Exception {

        PointParam requestRegister2 = new PointParam(2,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister2);

        PointParam requestRegister3 = new PointParam(3,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister3);

        PointParam requestRegister4 = new PointParam(4,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister4);


        PointParam requestRegister5 = new PointParam(5,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister5);


        PointParam requestRegister6 = new PointParam(6,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister6);


        PointParam requestRegister7 = new PointParam(7,SERVICEID,EVENT_REGISTER,OP_USERID);

        pointService.addPointAndGrow(requestRegister7);


        QueryPointCondition condition = new QueryPointCondition();
        condition.setPageNum(0);
        condition.setPageSize(5);
        QueryPointResult response = pointService.queryPoints(condition);

        assertEquals(5,response.getList().size());
        assertEquals(2,response.getPageCount());
        condition.setPageSize(0);
        QueryPointResult response1 = pointService.queryPoints(condition);
        assertEquals(6,response1.getList().size());

    }

    @Test
    public void testQueryUserPointLog() throws Exception {

        PointParam request = new PointParam(USERID,SERVICEID,EVENT_LOGIN,OP_USERID);

        pointService.addPoint(request);
        pointService.addPoint(request);
        pointService.addPoint(request);
        pointService.addPoint(request);
        pointService.addPoint(request);
        pointService.addPoint(request);
        pointService.addPoint(request);
        pointService.addPoint(request);
        pointService.addPoint(request);
        pointService.addPoint(request);
        pointService.addPoint(request);
        pointService.addPoint(request);
        Point p =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p);
        assertEquals(12,p.getPoint());

        QueryPointLogCondition condition  = new QueryPointLogCondition();
        condition.setUserId(USERID);
        PointLogResult response1 = pointService.queryUserPointLog(condition);
        assertEquals(12,response1.getList().size());
        condition.setPageSize(5);
        condition.setPageNum(0);
        PointLogResult response2 = pointService.queryUserPointLog(condition);
        assertEquals(5,response2.getList().size());

        condition.setPageSize(5);
        condition.setPageNum(5);
        PointLogResult response3 = pointService.queryUserPointLog(condition);
        assertEquals(5,response3.getList().size());
        assertEquals(3,response3.getPageCount());

        condition.setPageSize(10);
        condition.setPageNum(0);
        PointLogResult response4 = pointService.queryUserPointLog(condition);
        assertEquals(10,response4.getList().size());
        assertEquals(2,response4.getPageCount());

        condition.setPageSize(10);
        condition.setPageNum(10);
        PointLogResult response5 = pointService.queryUserPointLog(condition);
        assertEquals(2,response5.getList().size());
        assertEquals(2,response5.getPageCount());


    }

    @Test
    public void testQueryUserGrowthLog() throws Exception {


        PointParam request = new PointParam(USERID,SERVICEID,EVENT_LOGIN,OP_USERID);

        pointService.addGrowth(request);
        pointService.addGrowth(request);
        pointService.addGrowth(request);
        pointService.addGrowth(request);
        pointService.addGrowth(request);
        pointService.addGrowth(request);
        pointService.addGrowth(request);
        pointService.addGrowth(request);
        pointService.addGrowth(request);
        pointService.addGrowth(request);
        pointService.addGrowth(request);
        pointService.addGrowth(request);
        Point p =  pointService. queryUserPoint(SERVICEID, USERID);
        assertNotNull(p);
        assertEquals(24,p.getGrowth());

        QueryPointLogCondition condition  = new QueryPointLogCondition();
        condition.setUserId(USERID);
        PointLogResult response1 = pointService.queryUserGrowthLog(condition);
        assertEquals(12,response1.getList().size());
        condition.setPageSize(5);
        condition.setPageNum(0);
        PointLogResult response2 = pointService.queryUserGrowthLog(condition);
        assertEquals(5,response2.getList().size());

        condition.setPageSize(5);
        condition.setPageNum(5);
        PointLogResult response3 = pointService.queryUserGrowthLog(condition);
        assertEquals(5,response3.getList().size());
        assertEquals(3,response3.getPageCount());

        condition.setPageSize(10);
        condition.setPageNum(0);
        PointLogResult response4 = pointService.queryUserGrowthLog(condition);
        assertEquals(10,response4.getList().size());
        assertEquals(2,response4.getPageCount());

        condition.setPageSize(10);
        condition.setPageNum(10);
        PointLogResult response5 = pointService.queryUserGrowthLog(condition);
        assertEquals(2,response5.getList().size());
        assertEquals(2,response5.getPageCount());

    }

    @Test
    public void testAddPointExp() throws Exception {

        PointParam request = new PointParam(USERID,SERVICEID,"10",OP_USERID);
//        List<Variable> variables = new ArrayList<Variable>();
//        variables.add(Variable.createVariable("t1",new Float(10)));
//        variables.add(Variable.createVariable("t2",new Float(10)));
      //  System.out.println(ExpressionEvaluator.evaluate("t1*10+t2*2", variables));
//        request.setVariables(variables);
        Map<String, Float> map = new HashMap<String, Float>();
        map.put("t1",new Float(10)) ;
        map.put("t2",new Float(10)) ;
        request.setParams(map);
        pointService.addPoint(request);
        Point p =  pointService. queryUserPoint(SERVICEID , USERID);
        assertNotNull(p);
        assertEquals(120,p.getPoint());
        assertEquals(0,p.getGrowth());

    }
}