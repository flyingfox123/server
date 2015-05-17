package com.manyi.business.util.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileConst {
 
    private static final Properties properties= new Properties();
    public static Properties getFileConstProperties(){
        try {
            ClassLoader classLoader = FileConst.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("uploadConst.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}