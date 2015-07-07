package com.manyi.business.pay.common;

import com.manyi.common.util.ReadPropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;

/**
 * 信联接口配置信息
 * Author: WangPengfei
 * Review:
 * Data: 2015/5/29.
 */
public class XLConfig {

    private static final String CONF_FILE_NAME = "config.properties";
    public static final Properties PROPERTIES = ReadPropertiesUtil.readProperties(CONF_FILE_NAME);

    public static final String REQUEST_URL=PROPERTIES.getProperty("request_url"); //请求地址

    public static final String PARTNER_ID=PROPERTIES.getProperty("partner_id"); // 商户id

    public static final String SECRET_KEY=PROPERTIES.getProperty("secret_key"); // 签名时的私钥

    public static final String STRKEY=PROPERTIES.getProperty("strKey"); // 3DES密钥

}
