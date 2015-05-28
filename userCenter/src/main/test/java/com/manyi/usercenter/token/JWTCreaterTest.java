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
@ContextConfiguration(locations={"classpath:spring-usercenter-servlet-junit.xml"})
public class JWTCreaterTest {

    @Autowired
    JWTCreater jwtCreater;

    @Autowired
    JWTValidate jwtValidate;

    @Autowired
    UserService userService;

    @Test
    public void createTokenTest() {
        String userName ="test";
        BaseUser baseUser = userService.getUserByName(userName);
        String tokenid = jwtCreater.createToken(userName,baseUser.getSecretKey(),String.valueOf(baseUser.getId()));
        System.out.println(tokenid);
    }

    @Test
    public void tokenValidate() throws NoSuchAlgorithmException, SignatureException, JWTVerifyException, InvalidKeyException, IOException {
//        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZXN0IiwidGltZSI6MTQzMjA4NzYyMzA3MCwidXNlcklkIjoiMTUifQ.Z2Ol6q8aTynoL3BGvncLXUjMEOT2oTPvAbJQm-pT7r0";
//        boolean flag = jwtValidate.validate(token,"test");
       // String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhZG1pbiIsInRpbWUiOjE0MzIwOTA0NTgzOTgsInVzZXJJZCI6IjEifQ.ihNTx_tjIbKaN5ZEwz6vuhnryN7WN5yG-8EGP3qG0qk";
       // boolean flag = jwtValidate.validate(token,"admin");

        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZXN0IiwidGltZSI6MTQzMjEwMDUyOTk4MiwidXNlcklkIjoiMTUifQ.LyhppNitXZ3Lc1AvbFrJLci8ZFoE93NeOKp5RFKI7YQ";
        boolean flag = jwtValidate.validate(token,"test");
        System.out.println(flag);
    }
}
