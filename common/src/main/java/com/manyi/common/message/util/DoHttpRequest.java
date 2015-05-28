package com.manyi.common.message.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 向短信接口发送http请求
 * Created by zhaoyuxin on 2015/5/6 0006.
 */
public class DoHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(DoHttpRequest.class);

    public static String DoHttpRequest(String url,String param,String type) throws Exception {
        String result = "";
        BufferedReader in = null;
        if ("POST".equals(type)) {
            OutputStreamWriter out = null;
            try {
                URL realUrl = new URL(url);
                HttpURLConnection conn = null;

                conn = (HttpURLConnection) realUrl.openConnection();

                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod(type);    // POST方法

                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                //conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                conn.connect();

                // 获取URLConnection对象对应的输出流
                out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                // 发送请求参数
                out.write(param);
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
                logger.error("发送 POST 请求出现异常！" + e);
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
                    logger.error("发送 POST 请求出现异常！" + ex);
                    throw ex;
                }
            }

        }
        else {
            try {
                String urlName = url + "?" + param;
                URL realUrl = new URL(urlName);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                // 建立实际的连接
                conn.connect();
                // 获取所有响应头字段
                Map<String, List<String>> map = conn.getHeaderFields();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += "/n" + line;
                }
            } catch (Exception e) {
                logger.error("发送GET请求出现异常！" + e);
                throw e;
            }
            // 使用finally块来关闭输入流
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    logger.error("发送GET请求出现异常！" + ex);
                    throw ex;
                }
            }
        }
        return result;
    }
}
