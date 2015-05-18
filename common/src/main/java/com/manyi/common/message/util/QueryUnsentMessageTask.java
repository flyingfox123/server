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
 * Author  zhaoyuxin Reviewer:
 */
@Component
public class QueryUnsentMessageTask {

    private static final Logger logger = LoggerFactory.getLogger(QueryUnsentMessageTask.class);

    @Autowired
    public MessageServiceImpl messageServiceImpl;

    //短信队列
    public static ConcurrentLinkedQueue<MessageSend> messageSendQueue;

    //短信队列长度
    public static int messageLength = Integer.parseInt(ReadPropertiesUtil.readProperties("message.properties").getProperty("messageLength"));

    //是否开始查询
    public  static AtomicBoolean isBeginSend = new AtomicBoolean(false);

    public void run() {

        if (messageSendQueue == null) {
            messageSendQueue = new ConcurrentLinkedQueue<MessageSend>();
        }
        //队列为空且未满，则查询未发送短信入队列
        if (messageSendQueue.isEmpty() == true &&(isBeginSend.get()==false)) {
            {
                logger.info("开始查询");
                List<MessageSend> messageSendList = messageServiceImpl.queryUnsentMessage();
                logger.info("结束查询：未发送短信条数为" + messageSendList.size());
                for (MessageSend messageSend : messageSendList) {
                    messageSendQueue.offer(messageSend);
                }
            }
            isBeginSend.set(true);
        }
    }


}
