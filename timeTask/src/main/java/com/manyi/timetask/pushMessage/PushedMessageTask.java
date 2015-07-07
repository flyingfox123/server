package com.manyi.timetask.pushMessage;

import com.manyi.business.pay.check.CheckService;
import com.manyi.common.messagePush.PushMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Administrator on 2015/6/24.
 */
public class PushedMessageTask {

    @Autowired
    private PushMessageService pushMessageService;

    public void pushMessageStart(){
        pushMessageService.sendPublicMessage2All();
    }
}
