package com.manyi.business.util.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileConst {
 
    private static final Properties properties= new Properties();
    private static final Logger logger = LoggerFactory.getLogger(FileConst.class);

    public static Properties getFileConstProperties(){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("uploadConst.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("IO 异常" , e);
        }
        return properties;
    }
}