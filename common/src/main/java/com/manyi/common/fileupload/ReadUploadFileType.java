package com.manyi.common.fileupload;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.MimetypesFileTypeMap;


/**
 * 读取配置文件
 *
 * @author Owner
 */
public class ReadUploadFileType {


    /**
     * 判断该文件是否为上传的文件类型
     *
     * @param uploadfile
     * @return
     */
    public static Boolean readUploadFileType(File uploadfile,Properties properties) {
        if (uploadfile != null && uploadfile.length() > 0) {
            String ext = uploadfile.getName().substring(uploadfile.getName().lastIndexOf(".") + 1).toLowerCase();
            List<String> allowfiletype = new ArrayList<String>();
            for (Object key : properties.keySet()) {
                String value = (String) properties.get(key);
                String[] values = value.split(",");
                for (String v : values) {
                    allowfiletype.add(v.trim());
                }
            }
            // "Mime Type of gumby.gif is image/gif"
            return allowfiletype.contains(new MimetypesFileTypeMap().getContentType(uploadfile).toLowerCase()) && properties.keySet().contains(ext);
        }
        return true;
    }

    /**
     * 判断上传文件类型
     * @param items
     * @return
     */
    public static Boolean checkFileType(List<FileItem> items,Properties properties){
        properties=properties;
        Iterator<FileItem> itr = items.iterator();//所有的表单项
        while (itr.hasNext()){
            FileItem item = (FileItem) itr.next();//循环获得每个表单项
            if (!item.isFormField()){//如果是文件类型
                String name = item.getName();//获得文件名 包括路径啊
                if(name!=null){
                    File fullFile=new File(item.getName());
                    boolean isType = readUploadFileType(fullFile,properties);
                    if(!isType) return false;
                    break;
                }
            }
        }

        return true;
    }

}