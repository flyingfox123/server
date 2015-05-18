package com.manyi.common.message.util;

import com.manyi.common.message.bean.MessageSend;
import com.manyi.common.message.support.MessageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Author  zhaoyuxin Reviewer:
 */
@Component
public class SendMessageTask {

    private static final Logger logger = LoggerFactory.getLogger(SendMessageTask.class);

    @Autowired
    public MessageServiceImpl messageServiceImpl;

    public void run() {
        if (QueryUnsentMessageTask.messageSendQueue != null) {
            while (!QueryUnsentMessageTask.messageSendQueue.isEmpty()) {
                logger.info(Thread.currentThread().getName() + "开始发送，队列长度:" + QueryUnsentMessageTask.messageSendQueue.size());
                MessageSend messageSend = QueryUnsentMessageTask.messageSendQueue.poll();
                try {
                    if(messageSend!=null)
                    messageServiceImpl.sendMessage(messageSend);
                    logger.info("开始发送，短信id:" + messageSend.getId());
                } catch (Exception e) {
                    logger.error("发送失败手机号：" + messageSend.getMobile()+e.getMessage());
                    continue;
                }
                messageSend.setSendTime(new Date());
                messageServiceImpl.updateMessage(messageSend);
                logger.info(Thread.currentThread().getName()+"结束发送，队列长度:" + QueryUnsentMessageTask.messageSendQueue.size());
            }
            QueryUnsentMessageTask.isBeginSend.set(false);
        }
    }
}
