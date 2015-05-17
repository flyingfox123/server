package com.manyi.usercenter.token;

import com.auth0.jwt.JWTVerifyException;
import com.manyi.common.redis.RedisClientTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import static org.junit.Assert.*;
/**
 * Created by zhangyufeng on 2015/4/27 0027.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-usercenter-servlet-junit.xml","classpath:spring-common-servlet.xml"})
public class JWTCreaterTest {

    @Autowired
    JWTCreater jwtCreater;

    @Autowired
    JWTValidate jwtValidate;

    @Test
    public void createTokenTest() {
        String userName ="test";
        String tokenid = jwtCreater.createToken(userName,"","");
        System.out.println(tokenid);
    }

    @Test
    public void tokenValidate() throws NoSuchAlgorithmException, SignatureException, JWTVerifyException, InvalidKeyException, IOException {
        String tokenid="b1b45f68625440d59142c2d826745422";
//        boolean flag = jwtValidate.tokenValidate(tokenid);
//
//        assertTrue(flag);
    }
}
