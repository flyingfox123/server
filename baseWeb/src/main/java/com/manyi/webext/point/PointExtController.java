package com.manyi.webext.point;

import com.manyi.base.entity.Message;
import com.manyi.base.entity.State;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.bean.response.ResponseBean;
import com.manyi.point.PointService;
import com.manyi.point.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 积分对外控制层
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
@Controller
@RequestMapping("/point")
public class PointExtController {

    @Autowired
    private PointService pointService;


    private static  final int WECHAT_CHANNEL= 1;

    private static final int WEIBO_CHANNEL= 2;

    private static final int SMS_CHANNEL= 3;

    private static final int SUCCESS= 200;


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
    @RequestMapping("/select/pointLogs")
    @ResponseBody
    public ResponseBean queryUserPointLogs(@RequestBody QueryPointLogCondition condition) {

        PointLogResult result =  pointService.queryUserPointLog(condition);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setBody(result);
        responseBean.setState(State.SUCCESS.getString());
        return  responseBean ;
    }



    /**
     * 分享增加积分接口
     *
     * @return
     */
    @RequestMapping("/createSharedPoint")
    @ResponseBody
    public ResponseBean createSharedPoint(@RequestBody PointSharedRequest request) throws BusinessException {

        if(null == request || 0 == request.getUserId() || request.getSharedChannel() ==0)
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        PointParam param = new PointParam(request.getUserId());
        param.setServiceId(ServiceConstant.MANYI);
        param.setOpUserId(ServiceConstant.SYSTEM);

        if(request.getSharedChannel() == WECHAT_CHANNEL)
        {
            param.setEventCode(ServiceConstant.SHARED_WECHAT);

        }
        else if(request.getSharedChannel() == WEIBO_CHANNEL)
        {
            param.setEventCode(ServiceConstant.SHARED_WEIBO);
        }

        else if(request.getSharedChannel() ==SMS_CHANNEL)
        {
            param.setEventCode(ServiceConstant.SHARED_SMS);
        }
        else
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        pointService.addPoint(param);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(State.SUCCESS.getString());
        return  responseBean ;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/addPoint")
    @ResponseBody
    public Message addPoint(@RequestBody Point point) throws BusinessException {

        PointParam request = new PointParam(point.getUserId());
        request.setServiceId(ServiceConstant.MANYI);
        request.setOpUserId(ServiceConstant.SYSTEM);
        request.setEventCode("11");
        ConcurrentHashMap <String,Float> params1 = new ConcurrentHashMap<String, Float>();
        params1.put("t1", new Float(point.getGrowth())) ;
        params1.put("t2", new Float(point.getLevel())) ;
        request.setParams(params1);
        pointService.addPoint(request);
        Message message = new Message();
        message.setCodeMsg(SUCCESS);
        return    message;
    }


//    /**
//     * 查询所有用户
//     *
//     * @return
//     */
//    @RequestMapping("/usePoint")
//    @ResponseBody
//    public Message usePoint(@RequestBody  Point point) throws BusinessException {
////
////        UsePointParam usePoint = new UsePointParam(point.getUserId(),point.getServiceId(),"admin", point.getGrowth());
////        pointService.usePoint(usePoint);
////        Message message = new Message();
////        message.setCodeMsg(200);
////        return    message;
//    }
}
