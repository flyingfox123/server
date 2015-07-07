package com.manyi.common.message.util;

import com.manyi.common.message.bean.MessageSend;
import com.manyi.common.message.support.MessageServiceImpl;
import com.manyi.common.util.ReadPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhaoyuxin
 * @version 1.0.0 2015-05-05
 * @Description: 查询未发送短信
 * @reviewer:
 */
@Component
public class QueryUnsentMessageTask {

    private static final Logger logger = LoggerFactory.getLogger(QueryUnsentMessageTask.class);

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    //短信队列
    private final static ConcurrentLinkedQueue<MessageSend> messageSendQueue = new ConcurrentLinkedQueue<MessageSend>();

    //是否开始查询
    private final static AtomicBoolean isBeginSend = new AtomicBoolean(false);

    public void run() throws InterruptedException {

        //队列为空且未满，则查询未发送短信入队列
        if (messageSendQueue.isEmpty() == true && (isBeginSend.get() == false)) {
            logger.info("开始查询");
            List<MessageSend> messageSendList = messageServiceImpl.queryUnsentMessage();
            logger.info("结束查询：未发送短信条数为" + messageSendList.size());
            for (MessageSend messageSend : messageSendList) {
                messageSendQueue.offer(messageSend);
            }
            isBeginSend.set(true);
        }
    }

    public static ConcurrentLinkedQueue<MessageSend> getMessageSendQueue() {
        return messageSendQueue;
    }

    public static AtomicBoolean getIsBeginSend() {
        return isBeginSend;
    }


}
