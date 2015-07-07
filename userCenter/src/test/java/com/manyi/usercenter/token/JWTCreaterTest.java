package com.manyi.usercenter.token;

import com.auth0.jwt.JWTVerifyException;
import com.manyi.common.redis.RedisClientTemplate;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.support.entity.BaseUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
@ContextConfiguration(locations={"classpath:spring-usercenter-servlet-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class JWTCreaterTest {

    @Autowired
    JWTCreater jwtCreater;

    @Autowired
    JWTValidate jwtValidate;

    @Autowired
    UserService userService;

    @Test
    public void createTokenTest() {
        String userName ="admin";
        BaseUser baseUser = userService.getUserByName(userName);
        String token = jwtCreater.createToken(userName,baseUser.getSecretKey(),String.valueOf(baseUser.getId()));
        System.out.println(token);
    }

    @Test
    public void tokenValidate() throws NoSuchAlgorithmException, SignatureException, JWTVerifyException, InvalidKeyException, IOException {
//        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZXN0IiwidGltZSI6MTQzMjA4NzYyMzA3MCwidXNlcklkIjoiMTUifQ.Z2Ol6q8aTynoL3BGvncLXUjMEOT2oTPvAbJQm-pT7r0";
//        boolean flag = jwtValidate.validate(token,"test");
       // String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhZG1pbiIsInRpbWUiOjE0MzIwOTA0NTgzOTgsInVzZXJJZCI6IjEifQ.ihNTx_tjIbKaN5ZEwz6vuhnryN7WN5yG-8EGP3qG0qk";
       // boolean flag = jwtValidate.validate(token,"admin");

        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxNTM4ODYxOTM5MSIsInRpbWUiOjE0MzMzMTUwMTUwMzYsInVzZXJJZCI6IjY2In0.aG2oNWI8W5sDohFBGBkoi9gabndaube1gd_DxDkl1KA";
        boolean flag = jwtValidate.validate(token,"15388619391");
        System.out.println(flag);
    }
}
