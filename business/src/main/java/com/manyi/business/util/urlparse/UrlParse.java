package com.manyi.business.util.urlparse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by zhangyufeng on 2015/4/14 0014.
 */
public class UrlParse {
    private static final Properties properties = new Properties();
    private  static final Logger logger = LoggerFactory.getLogger(UrlParse.class);
    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("uploadConst.properties"));
        } catch (IOException e) {
            logger.error("读取配置文件失败",e);
        }
    }

    public static String getValue(String key) {
        return (String)properties.get(key);
    }
}
