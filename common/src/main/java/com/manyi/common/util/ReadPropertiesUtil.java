package com.manyi.common.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by zhangyufeng on 2015/4/23 0023.
 */
public class ReadPropertiesUtil {

    public static Properties readProperties(String name){
        Properties properties = new Properties();
        try {
            InputStream is = ReadPropertiesUtil.class.getClassLoader().getResourceAsStream(name);
            properties.load(new InputStreamReader(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
