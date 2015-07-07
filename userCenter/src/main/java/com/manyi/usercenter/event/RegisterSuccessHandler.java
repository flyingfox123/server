package com.manyi.usercenter.event;

import com.manyi.base.exception.BusinessException;
import com.manyi.point.PointService;
import com.manyi.point.bean.PointParam;
import com.manyi.point.bean.ServiceConstant;
import com.manyi.usercenter.user.bean.UserBean;
import com.manyi.usercenter.user.support.entity.BaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author liuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
@Component
public class RegisterSuccessHandler  implements ApplicationListener<RegisterSuccessEvent> {

    @Autowired
    private PointService pointService;


    @Autowired
    private ApplicationContext applicationContext;

    private static Logger logger = LoggerFactory.getLogger(RegisterSuccessHandler.class);

    @Async
    @Override
    public void onApplicationEvent(final RegisterSuccessEvent registerSuccessEvent) {
        System.out.println(applicationContext.getClass());
        BaseUser user = ( BaseUser) registerSuccessEvent.getSource();
        addRegisterPoint(user);

    }

    /**
     * 成功后增加积分
     * @param user
     */
   private void  addRegisterPoint( BaseUser user)
   {
       PointParam param = new PointParam(user.getId());
       param.setServiceId(ServiceConstant.MANYI);
       param.setOpUserId(ServiceConstant.SYSTEM);
       param.setEventCode(ServiceConstant.REGISTER);
       try {
           pointService.addPoint(param);
       } catch (BusinessException e) {
           logger.error("add point failed",e);
       }
  }

//    /**
//     * 成功后推送消息
//     */
//    private void sendFinishOrderMessage()
//    {
//
//    }
//
//
//    /**
//     * 成功后发送短信
//     */
//    private void sendFinishOrderSMS()
//    {
//
//    }


}

