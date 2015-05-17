package com.manyi.common.util;

import org.junit.Test;

import java.util.Properties;

/**
 * Created by zhangyufeng on 2015/5/12 0012.
 */
public class ReadPropertiesUtilTest {
    @Test
    public void testReadProperties(){
        Properties properties = ReadPropertiesUtil.readProperties("message.properties");
        String sign = (String)properties.get("sign");
        System.out.println(sign);
    }
}
