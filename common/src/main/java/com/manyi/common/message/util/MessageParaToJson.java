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
 * @Description: 组装发送短信需要的json格式的参数
 * @author zhaoyuxin
 * @version: 1.0.0  2015-05-05.
 * @reviewer:
 */
public class MessageParaToJson {

    //请求代码
    private static String reqCode=ReadPropertiesUtil.readProperties("message.properties").getProperty("reqCode");
    //令牌
    private static String tokenId= ReadPropertiesUtil.readProperties("message.properties").getProperty("tokenId");
    //签名
    private static String sign=ReadPropertiesUtil.readProperties("message.properties").getProperty("sign");
    //发送通道号
    private static String channelID=ReadPropertiesUtil.readProperties("message.properties").getProperty("channelID");

    public static String messageParaToJson(String mobile, String content) throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String sendTime = dateFormat.format(date);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
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
