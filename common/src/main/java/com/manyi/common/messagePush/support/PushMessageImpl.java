package com.manyi.common.messagePush.support;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.manyi.common.message.support.dao.MessageDao;
import com.manyi.common.messagePush.Jpush;
import com.manyi.common.messagePush.PushMessageService;
import com.manyi.common.messagePush.bean.MessageType;
import com.manyi.common.messagePush.bean.PushedMessage;
import com.manyi.common.messagePush.bean.QueryPushMessageCondition;
import com.manyi.common.messagePush.support.dao.PushMessageDao;
import com.manyi.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
@Service
public class PushMessageImpl implements PushMessageService {


    @Autowired
    private PushMessageDao pushMessageDao;


    private  static final Logger logger = LoggerFactory.getLogger(PushMessageImpl.class);


    @Override
    public void addPushedMessage(PushedMessage message) {

        pushMessageDao.addPushedMessage(message);
    }

    @Override
    public List<PushedMessage> queryPersonalMessage(long userId) {

        QueryPushMessageCondition condition = new QueryPushMessageCondition();
        condition.setUserId(userId);
        return pushMessageDao.queryPushedMessage(condition);
    }

    @Override
    public List<PushedMessage> queryPublicMessage() {
        QueryPushMessageCondition condition = new QueryPushMessageCondition();
        condition.setType(MessageType.PUBLIC);
        condition.setIsSended(true);
        condition.setCurrentTime(DateUtil.getCurrentString(new Date()));
        return pushMessageDao.queryPushedMessage(condition);
    }

    @Override
    public void updatePushedMessage(PushedMessage message) {

        pushMessageDao.updatePushedMessage(message);
    }

    @Override
    public void sendPublicMessage2All() {

        QueryPushMessageCondition condition = new QueryPushMessageCondition();
        condition.setType(MessageType.PUBLIC);
        condition.setCurrentTime(DateUtil.getCurrentString(new Date()));
        condition.setIsSended(false);
        List<PushedMessage>  list = pushMessageDao.queryPushedMessage(condition);

        if(null == list)
        {
            return;
        }

        for(PushedMessage message : list)
        {
            Map<String,String> extras = new HashMap<String,String>();
            extras.put("url",message.getUrl());
            boolean result = Jpush.pushMessageToAll(message.getContent(),message.getTitle(),extras);
            if(result)
            {
                message.setIsSended(true);
                pushMessageDao.updatePushedMessage(message);
            }
            else
            {
                logger.error("Push message to all failed");
            }
        }
    }
}
