package com.manyi.common.message.util;

import com.manyi.common.message.bean.MessageSend;
import com.manyi.common.message.support.MessageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Description: 发送短信任务
 * @author zhaoyuxin
 * @version 1.0.0  2015-05-05.
 * @reviewer:
 */
@Component
public class SendMessageTask {

    private static final Logger logger = LoggerFactory.getLogger(SendMessageTask.class);

    @Autowired
    public MessageServiceImpl messageServiceImpl;

    public void run() {
        if (QueryUnsentMessageTask.getMessageSendQueue() != null) {
            while ((!QueryUnsentMessageTask.getMessageSendQueue().isEmpty())&&(QueryUnsentMessageTask.getIsBeginSend().get()==true)) {
//                logger.info(Thread.currentThread().getName() + "开始发送，队列长度:" + QueryUnsentMessageTask.getMessageSendQueue().size());
                MessageSend messageSend = QueryUnsentMessageTask.getMessageSendQueue().poll();
                try {
                    if(!StringUtils.isEmpty(messageSend))
                    messageServiceImpl.sendMessage(messageSend);
//                    logger.info("开始发送，短信id:" + messageSend.getId());
                } catch (Exception e) {
                    logger.error("发送失败:" ,e);
                    continue;
                }
                messageSend.setSendTime(new Date());
                messageServiceImpl.updateMessage(messageSend);
//                logger.info(Thread.currentThread().getName() + "结束发送，队列长度:" + QueryUnsentMessageTask.getMessageSendQueue().size());
            }
            QueryUnsentMessageTask.getIsBeginSend().set(false);
        }
    }
}
