package com.manyi.webext.pushMessage;

import com.manyi.base.entity.State;
import com.manyi.common.bean.response.ResponseBean;
import com.manyi.common.messagePush.PushMessageService;
import com.manyi.common.messagePush.bean.PushedMessage;
import com.manyi.point.bean.Point;
import com.manyi.point.bean.QueryPointCondition;
import com.manyi.point.bean.QueryPointResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/6/24.
 */
@Controller
@RequestMapping("/pushMessage")
public class PushMessageExtController {

    private final static Logger logger = LoggerFactory.getLogger(PushMessageExtController.class);

    @Autowired
    private PushMessageService pushMessageService;

    /**
     *
     *  查询用户消息
     * @return
     */
    @RequestMapping("/queryUserMessage")
    @ResponseBody
    public ResponseBean queryUserMessage(@RequestBody PushedMessage message) {
        List<PushedMessage> personalList = pushMessageService.queryPersonalMessage(message.getUserId());
        List<PushedMessage> publicList =  pushMessageService.queryPublicMessage();
        personalList.addAll(publicList);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setBody(personalList);
        responseBean.setState(State.SUCCESS.getString());
        return  responseBean ;
    }


    /**
     *
     *  更新阅读状态
     * @return
     */
    @RequestMapping("/updateReadMark")
    @ResponseBody
    public ResponseBean updateReadMark(@RequestBody PushedMessage message) {
        message.setReadMark(true);
        pushMessageService.updatePushedMessage(message);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(State.SUCCESS.getString());
        return  responseBean ;
    }

}
