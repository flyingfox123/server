package com.manyi.point.controller;

import com.manyi.base.entity.Message;
import com.manyi.base.exception.BusinessException;
import com.manyi.point.PointService;
import com.manyi.point.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.expression.datameta.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2015/1/6.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({
//        "classpath:config/spring-base.xml",
//        "classpath:config/mybatis-config.xml",
//        "classpath:config/spring-servlet.xml"
//})
@Controller
@RequestMapping("/point")
public class PointController {

    private final static Logger logger = LoggerFactory.getLogger(PointController.class);

    @Autowired
    private PointService pointService;

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/select/points")
    @ResponseBody
    public  List<Point> queryPoints( QueryPointCondition condition) {

        QueryPointResult result =  pointService.queryPoints(condition);

        return result.getList();
    }


    /**
     * 查询日志
     *
     * @return
     */
    @RequestMapping(value = {"/select/pointlogs"})
    @ResponseBody
    public  List<PointLogBean> queryUserPointLogs( int userId) {
        QueryPointLogCondition condition = new   QueryPointLogCondition();
        condition.setUserId(userId);
//        condition.setPointId(point.getId());
        PointLogResult result =  pointService.queryUserPointLog(condition);
        return result.getList();
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/addPoint")
    @ResponseBody
    public Message addPoint(@RequestBody Point point) throws BusinessException {

        PointParam request = new PointParam(point.getUserId(),point.getServiceId(),"11","admin");

        Map<String,Float> params1 = new HashMap<String, Float>();
        params1.put("t1", new Float(point.getGrowth())) ;
        params1.put("t2", new Float(point.getLevel())) ;
        request.setParams(params1);
        int res = pointService.addPoint(request);
        Message message = new Message();
        message.setCodeMsg(200);
        return    message;
    }


    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/usePoint")
    @ResponseBody
    public Message usePoint(@RequestBody  Point point) throws BusinessException {

        UsePointParam usePoint = new UsePointParam(point.getUserId(),point.getServiceId(),"admin", point.getGrowth());
        pointService.usePoint(usePoint);
        Message message = new Message();
        message.setCodeMsg(200);
        return    message;
    }
}
