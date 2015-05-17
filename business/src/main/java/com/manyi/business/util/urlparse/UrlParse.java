package com.manyi.business.util.urlparse;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by zhangyufeng on 2015/4/14 0014.
 */
public class UrlParse {
    private static final Properties properties = new Properties();
    static {
        try {
            properties.load(UrlParse.class.getClassLoader().getResourceAsStream("uploadConst.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return (String)properties.get(key);
    }
}
