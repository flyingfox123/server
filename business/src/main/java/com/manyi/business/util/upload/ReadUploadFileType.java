package com.manyi.business.util.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class ReadUploadFileType {

    private static final Properties properties= new Properties();

    public static final Logger logger = LoggerFactory.getLogger(ReadUploadFileType.class);

    public static Properties getFileTypeProperties(){
        try {
            ClassLoader classLoader =  Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("upload_file_type.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("IO 异常 " , e);
        }
        return properties;
    }

}