package com.manyi.business.util.upload;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * 读取配置文件
 *
 * @author Owner
 */
public class ReadUploadFileType {

    private static final Properties properties= new Properties();
    public static Properties getFileTypeProperties(){
        try {
            ClassLoader classLoader = FileConst.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("upload_file_type.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}