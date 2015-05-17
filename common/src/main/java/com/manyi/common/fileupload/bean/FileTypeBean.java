package com.manyi.common.fileupload.bean;

import java.util.Properties;

/**
 * Created by zhangyufeng on 2015/4/13 0013.
 */
public class FileTypeBean {
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public FileTypeBean(Properties properties) {
        this.properties=properties;
    }
    public String getValue(String key){
        String value = (String)properties.get(key);
        return value.trim();
    }
}
