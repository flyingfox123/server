package com.manyi.common.message.util;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.util.ReadPropertiesUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 向短信接口发送http请求
 * Created by zhaoyuxin on 2015/5/6 0006.
 */
public class DoHttpRequest {
    //请求url
    public static String smsHttpUrl = ReadPropertiesUtil.readProperties("message.properties").getProperty("smsHttpUrl");

    public static void DoHttpRequest(String smsJson, String mobile) throws Exception {
        boolean isMobileNO = IsMobileNo.isMobileNO(mobile);
        if (isMobileNO == false) {
            throw new BusinessException(Type.WRONG_PHONENO);
        }
        String json = "json=" + smsJson;
        BufferedReader in = null;
        String result = "";
        OutputStreamWriter out = null;
        try {
            URL realUrl = new URL(smsHttpUrl);
            HttpURLConnection conn = null;

            conn = (HttpURLConnection) realUrl.openConnection();

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST方法

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.connect();

            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            out.write(json);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
            throw e;
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                throw ex;
            }
        }

    }
}