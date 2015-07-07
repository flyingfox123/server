package com.manyi.business.pay.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;


/**
 * @Description:
 * @author ZhangYufeng
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class DownLoadFile {

    private static final Logger logger = LoggerFactory.getLogger(DownLoadFile.class);

    /**
     * 通过httpClient下载文件
     * @param url 文件路径
     * @param destFile 文件存放位置
     */
    public static void doDownLoadFile(String url,String destFile){

        CloseableHttpClient httpClient= HttpClients.createDefault();;
        InputStream input =null;
        FileOutputStream fout=null;
        try {
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpget);

            HttpEntity entity = response.getEntity();
            input = entity.getContent();
            File file = new File(destFile);
            fout = new FileOutputStream(file);
            int len = -1;
            byte[] tmp = new byte[Constant.BYTESIZE];
            while ((len = input.read(tmp)) != -1) {
                fout.write(tmp, 0, len);
            }
            if (fout!=null){
                fout.flush();
                fout.close();
            }
        }catch (IOException ex){
            logger.error("下载文件失败",ex);
        }finally {
            // 关闭低层流。
            try {
                if (input!=null){
                    input.close();
                }
                if (httpClient!=null){
                    httpClient.close();
                }
                if (fout!=null){
                    fout.close();
                }

            }catch (IOException e){
                logger.error("关闭流异常",e);
            }
            finally {
                if (fout!=null){
                    try {
                        fout.close();
                    } catch (IOException e) {
                        logger.error("关闭流异常",e);
                    }

                }
            }
        }
    }
}
