package com.manyi.business.event;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.google.gson.Gson;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.etc.bean.Etc;
import com.manyi.business.etc.bean.EtcDescription;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.dao.OrderDao;
import com.manyi.common.message.MessageService;
import com.manyi.common.message.util.MessageBindPara;
import com.manyi.common.messagePush.Jpush;
import com.manyi.common.messagePush.PushMessageService;
import com.manyi.common.messagePush.bean.MessageType;
import com.manyi.common.messagePush.bean.PushedMessage;
import com.manyi.common.util.DateUtil;
import com.manyi.point.PointService;
import com.manyi.point.bean.PointParam;
import com.manyi.point.bean.ServiceConstant;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.support.entity.BaseUser;
import com.manyi.usercenter.user.support.entity.Individual;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
@Component
public class OrderSuccessHandler  implements ApplicationListener<OrderSuccessEvent> {

    @Autowired
    private PointService pointService;

    @Autowired
    private MessageService messageService;


    @Autowired
    private UserService userService;

    @Autowired
    private PushMessageService pushMessageService;



    public static final String  ETC_PRECHARGE_TEMPLATE_ID = "etc_precharge_jpush";



    public static final String TITLE = "预充值成功提醒";

    Gson gson = new Gson();



    private static Logger logger = LoggerFactory.getLogger(OrderSuccessHandler.class);


    @Async
    @Override
    public void onApplicationEvent(final OrderSuccessEvent orderSuccessEvent) {

        Order order = (Order) orderSuccessEvent.getSource();
        addFinishOrderPoint(order);
        try {
            sendFinishOrderMessage(order);
        } catch (BusinessException e) {
            logger.error("send Message failed", e);
        } catch (APIRequestException e) {
            logger.error("Call jpush API Failed", e);
        } catch (APIConnectionException e) {
            logger.error("JPush connect failed", e);
    }
    }

    /**
     * 成功后增加积分
     * @param order
     */
   private void  addFinishOrderPoint(Order order)
   {
       ConcurrentHashMap<String,Float> params = new ConcurrentHashMap<String,Float>();
       params.put("t1",order.getPayableAmount().floatValue());
       PointParam param = new PointParam(order.getUserId());
       param.setServiceId(ServiceConstant.MANYI);
       param.setOpUserId(ServiceConstant.SYSTEM);
       param.setEventCode(ServiceConstant.FINISH_ORDER);
       param.setParams(params);
       try {
           pointService.addPoint(param);
       } catch (BusinessException e) {
           logger.error("add point failed",e);
       }
  }

    /**
     * 成功后推送消息
     */
    private void sendFinishOrderMessage(Order order) throws BusinessException, APIConnectionException, APIRequestException {
       for(OrderItem item : order.getItemList())
       {
           if(item.getType().equals(BusinessType.ETC) && item.getState().equals(OrderItemStatus.SUCCESS))
           {
               String des = item.getDescription();
               if(!StringUtils.isEmpty(des))
               {
                   EtcDescription description = gson.fromJson(des,EtcDescription.class);
                   Individual individual =  userService.getUserByEtc(description.getETCCode());
                   Individual individual1 = userService.getIndividualById(order.getUserId());
                   //如果系统中存在此用户则推送消息
                   if(null != individual && null != individual1)
                   {
                       String template = messageService.queryTemplate(ETC_PRECHARGE_TEMPLATE_ID);
                       Map<String,String> paras = new HashMap<String,String> ();
                       paras.put("username",individual1.getDriverName());
                       paras.put("phone",individual1.getPhone());
                       paras.put("time", DateUtil.getCurrentString(new Date()));
                       paras.put("amount",item.getPayableAmount().toString());
                       String content =  MessageBindPara.MessageBindPara(template, paras);
                       boolean success =Jpush.pushAndroidMessage(individual.getLoginName(), TITLE, content, null);
                       PushedMessage message = new PushedMessage();
                       message.setUserId(individual.getUserId());
                       message.setContent(content);
                       message.setReadMark(false);
                       message.setSendTime(DateUtil.getCurrentString(new Date()));
                       message.setTitle(TITLE);
                       message.setType(MessageType.PERSONAL);
                       message.setIsSended(false);
                       if(success)
                       {
                           message.setIsSended(true);
                       }
                       pushMessageService.addPushedMessage(message);

                   }

               }
           }
       }
    }


}

