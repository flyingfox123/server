package com.manyi.common.message.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manyi.common.message.bean.DataJson;
import com.manyi.common.message.bean.ReqHeaderJson;
import com.manyi.common.util.ReadPropertiesUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 组装发送短信需要的json格式的参数
 * Created by zhaoyuxin on 2015/5/5.
 */
public class MessageParaToJson {

    //请求代码
    public static String reqCode=ReadPropertiesUtil.readProperties("message.properties").getProperty("reqCode");
    //令牌
    public static String tokenId= ReadPropertiesUtil.readProperties("message.properties").getProperty("tokenId");
    //签名
    public static String sign=ReadPropertiesUtil.readProperties("message.properties").getProperty("sign");
    //发送通道号
    public static String channelID=ReadPropertiesUtil.readProperties("message.properties").getProperty("channelID");

    public static String messageParaToJson(String mobile, String content) throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String sendTime = df.format(date);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        //Object obj=new Object();
        ReqHeaderJson reqHeaderJson = new ReqHeaderJson();
        reqHeaderJson.setReqCode(reqCode);
        reqHeaderJson.setReqTime(sendTime);
        reqHeaderJson.setTokenId(tokenId);
        reqHeaderJson.setTransactionId(uuid.toString());
        DataJson datajson = new DataJson();
        datajson.setMobile(mobile);
        datajson.setContent(content);
        datajson.setChannelID(channelID);
        datajson.setSign(sign);
        map.put("reqHeader",reqHeaderJson);
        map.put("data",datajson);
        String json = objectMapper.writeValueAsString(map);
        return json;
    }
}
