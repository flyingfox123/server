package com.manyi.business.util.upload;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.util.urlparse.UrlParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @Description:
 * @author zhangyufeng on 2015/4/15 0015.
 * @version 1.0.0
 * @reviewer:
 */
public class FileUpload {

    private  static final Logger logger = LoggerFactory.getLogger(FileUpload.class);

    public static void fileUpload(String fileUrl,String fileName) throws BusinessException {
        if (fileUrl==null || fileName==null){
            throw new BusinessException(Type.NO_EXCEPTIONMSG);
        }
        String savePath = UrlParse.getValue("savePath");
        String fileTempPath = UrlParse.getValue("fileTempPath");
        FileInputStream fileInputStream=null;
        FileOutputStream fileOutputStream=null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fileInputStream = new FileInputStream(new File(fileTempPath+fileUrl+fileName));
            File saveFileDir = new File(savePath+fileUrl);
            if (!saveFileDir.exists()){
                saveFileDir.mkdirs();
            }
            File file = new File(savePath+fileUrl+fileName);
//            if (!file.exists()){
//                file.createNewFile();
//            }
            if (file.exists()) {
                file.delete();
                file = new File(savePath+fileUrl+fileName);
            }
            fileOutputStream = new FileOutputStream(file);
            in = fileInputStream.getChannel();
            out = fileOutputStream.getChannel();
            in.transferTo(0,in.size(),out);
        } catch (FileNotFoundException e) {
            logger.error("上传的文件不存在", e);
            throw new BusinessException("上传的文件不存在");
        } catch (IOException e) {
            logger.error("读取文件失败",e);
        } finally {
            try {
                if (fileInputStream !=null){
                    fileInputStream.close();
                }
                if (fileOutputStream != null){
                    fileOutputStream.close();
                }
                if (in != null){
                    in.close();
                }
                if (out != null){
                    out.close();
                }

            }catch (IOException e){
                logger.error("关闭文件异常",e);
            }
        }
    }
}
