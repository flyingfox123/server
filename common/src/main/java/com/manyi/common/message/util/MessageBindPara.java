package com.manyi.common.message.util;

import java.util.Iterator;
import java.util.Map;

/**
 * @Description: 短信绑定参数
 * @author zhaoyuxin
 * @version: 1.0.0  2015-04-28.
 * @reviewer:
 */
public  class MessageBindPara {
    public  static String MessageBindPara(String Content,Map<String,String> Paras) {
        Iterator<Map.Entry<String, String>> it = Paras.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            Content = Content.replaceAll(String.format("<%s>", entry.getKey()), entry.getValue());
        }
        return Content;
    }

}