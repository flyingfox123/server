package com.manyi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by zhangyufeng on 2015/4/23 0023.
 */
public class ReadPropertiesUtil {
    private  static final Logger logger = LoggerFactory.getLogger(ReadPropertiesUtil.class);

    public static Properties readProperties(String name){
        Properties properties = new Properties();
        InputStreamReader inputStreamReader=null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
            inputStreamReader = new InputStreamReader(is);
            properties.load(inputStreamReader);
        } catch (IOException e) {
            logger.error("读取配置文件异常", e);
        }finally {
            try {
                if (is != null){
                    is.close();
                }
                if (inputStreamReader != null){
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                logger.error("关闭文件异常",e);
            }

        }
        return properties;
    }

}
