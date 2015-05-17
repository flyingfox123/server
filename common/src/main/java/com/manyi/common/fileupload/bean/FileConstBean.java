package com.manyi.common.fileupload.bean;

import java.util.Properties;

/**
 * Created by zhangyufeng on 2015/4/12 0012.
 */
public class FileConstBean {
    private Properties properties;
    public FileConstBean(Properties properties) {
        this.properties=properties;
    }
    public String getValue(String key){
        String value = (String)properties.get(key);
        return value.trim();
    }
}
