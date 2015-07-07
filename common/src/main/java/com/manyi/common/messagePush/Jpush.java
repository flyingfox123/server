package com.manyi.common.messagePush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.manyi.common.util.ReadPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class Jpush {


    private Jpush()
    {}


    public  static final String MASTER_SECRET =
            ReadPropertiesUtil.readProperties("message.properties").getProperty("masterSecret");

    public static final String APP_KEY =
            ReadPropertiesUtil.readProperties("message.properties").getProperty("appKey");
    public static final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, 3);

    public static final Logger logger = LoggerFactory.getLogger(Jpush.class);
    // For push, all you need do is to build PushPayload object.

    public  static final String TAG ="MANYI";

    public  static boolean pushAndroidMessage(String userKey,String title , String content,Map<String,String> extras)  {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(userKey))
                .setNotification(Notification.android(content, title, extras))
                .build();
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("Api connect failed ", e);
        } catch (APIRequestException e) {
            logger.error("Api request exception ", e);

        }
        if(null == result)
        {
            return false;
        }
        else {
            return  result.isResultOK();
        }

    }


    public  static boolean pushMessageToAll(String content,String title,Map<String, String> extras)  {
//        PushPayload payload = PushPayload.alertAll(content);;
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(TAG))
                .setNotification(Notification.android(content, title, extras))
                .build();
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("Api connect failed ", e);
        } catch (APIRequestException e) {
            logger.error("Api request exception ", e);
        }
        if(!StringUtils.isEmpty(result))
        {
            return  result.isResultOK();
        }
        return  false;
    }


}
