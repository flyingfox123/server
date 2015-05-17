package com.manyi.common.message.util;

import java.util.Iterator;
import java.util.Map;

/**
 * 短信绑定参数
 * Created by Zhaoyuxin on 2015/4/28 0028.
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