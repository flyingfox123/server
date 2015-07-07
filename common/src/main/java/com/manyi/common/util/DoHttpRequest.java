package com.manyi.common.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.Properties;
/**
 * @Description:
 * @author WangPengfei
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class DoHttpRequest {

    private DoHttpRequest(){}

    private static final Logger logger = LoggerFactory.getLogger(DoHttpRequest.class);

    private static volatile String   encoding = null;

    // 线程安全管理类
    private static final HttpConnectionManager connectionManager;

    private static HttpClient client;

    static {

        HttpConnectionManagerParams params = loadHttpConfFromFile();

        connectionManager = new MultiThreadedHttpConnectionManager();

        connectionManager.setParams(params);

        client = new HttpClient(connectionManager);
    }

    /**
     * 读取Http配置文件参数
     * @return
     */
    private static HttpConnectionManagerParams loadHttpConfFromFile(){
        Properties props = null;
        try {
            props = ReadPropertiesUtil.readProperties("httputil.properties");
        } catch (Exception e) {
            logger.error("The DoHttpReq loadProperty is wrong, Exception=" + e);
        }

        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        if (props != null) {
            encoding = props.getProperty("http.content.encoding");
            params.setConnectionTimeout(Integer.parseInt(props.getProperty("http.connection.timeout")));
            params.setSoTimeout(Integer.parseInt(props.getProperty("http.so.timeout")));
            params.setStaleCheckingEnabled(Boolean.parseBoolean(props.getProperty("http.stale.check.enabled")));
            params.setTcpNoDelay(Boolean.parseBoolean(props.getProperty("http.tcp.no.delay")));
            params.setDefaultMaxConnectionsPerHost(Integer.parseInt(props.getProperty("http.default.max.connections.per.host")));
            params.setMaxTotalConnections(Integer.parseInt(props.getProperty("http.max.total.connections")));
            params.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
            return params;
        } else {
            return null;
        }
    }

    /**
     * 创建http post请求
     * @param url
     * @param reqString 请求参数，String类型
     */
    public static String doPostRequest(String url, String reqString) {

        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");

        String respString = null;
        try {
            byte[] reqBytes = reqString.getBytes(encoding);
            RequestEntity requestEntity = new ByteArrayRequestEntity(reqBytes);
            method.addRequestHeader("Connection", "Keep-Alive");
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            method.setRequestEntity(requestEntity);
            client.executeMethod(method);

            if (method.getStatusCode() == HttpStatus.SC_OK) {
                respString = method.getResponseBodyAsString();
            }

        } catch (SocketTimeoutException e) {
            logger.error("The doHttpRequest is wrong, SocketTimeoutException = " + e);
        } catch (HttpException e) {
            logger.error("The doHttpRequest is wrong, HttpException = " + e);
        } catch (UnsupportedEncodingException e) {
            logger.error("The doHttpRequest is wrong, UnsupportedEncodingException = " + e);
        } catch (IOException e) {
            logger.error("The doHttpRequest is wrong, IOException = " + e);
        } finally {
            method.releaseConnection();
        }
        return respString;

    }

    /**
     * 创建http get请求
     * @param url
     * @param reqString 请求参数，String类型
     */
    public static String doGetRequest(String url, String reqString) {

        GetMethod method = new GetMethod(url + "?" + reqString);

        String respString = null;

        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                respString = method.getResponseBodyAsString();
            }
        } catch (SocketTimeoutException e) {
            logger.error("The doGetRequest is wrong, SocketTimeoutException = " + e);
        } catch (HttpException e) {
            logger.error("The doHttpRequest is wrong, HttpException = " + e);
        } catch (UnsupportedEncodingException e) {
            logger.error("The doHttpRequest is wrong, UnsupportedEncodingException = " + e);
        } catch (IOException e) {
            logger.error("The doHttpRequest is wrong, IOException = " + e);
        } finally {
            method.releaseConnection();
        }

        return respString;
    }

}
