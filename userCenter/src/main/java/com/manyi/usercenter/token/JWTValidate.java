package com.manyi.usercenter.token;

import com.auth0.jwt.JWTExpiredException;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.manyi.common.redis.RedisClientTemplate;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.support.entity.BaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

/**
 * Created by zhangyufeng on 2015/4/27 0027.
 */
@Service
public class JWTValidate {
    private final static Logger logger = LoggerFactory.getLogger(JWTValidate.class);
    @Autowired
    private RedisClientTemplate redisClient;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTCreater jwtCreater;


//    public String validate(String token,String loginName) throws SignatureException, NoSuchAlgorithmException, JWTVerifyException, InvalidKeyException, IOException {
//        String newToken=null;
//        BaseUser baseUser = userService.getUserByName(loginName);
//        try {
//
//            JWTVerifier jwtVerifier = new  JWTVerifier(baseUser.getSecretKey());
//            Map storeMap = jwtVerifier.verify(token);
//            String storeToken = redisClient.get((String)storeMap.get("userId"));
//        }catch (JWTExpiredException e){
//            newToken = jwtCreater.createToken(loginName, baseUser.getSecretKey(), String.valueOf(baseUser.getId()));
//        }
//        return newToken;
//    }

    /**
     * token失效之后用户重新登录
     * @param token
     * @param loginName
     * @return
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws JWTVerifyException
     * @throws InvalidKeyException
     * @throws IOException
     */
    public boolean validate(String token,String loginName) throws NoSuchAlgorithmException, JWTVerifyException, InvalidKeyException, IOException {
        BaseUser baseUser = userService.getUserByName(loginName);
        try {

            JWTVerifier jwtVerifier = new  JWTVerifier(baseUser.getSecretKey());
            Map storeMap = jwtVerifier.verify(token);
            String storeToken = redisClient.get((String)storeMap.get("userId"));
            if(storeToken!=null&&token.equals(storeToken)){
                return true;
            }
        }catch (JWTExpiredException e){
            logger.error("token超过有效期",e);
            //newToken = jwtCreater.createToken(loginName, baseUser.getSecretKey(), String.valueOf(baseUser.getId()));
            return false;
        }catch (SignatureException e){
            logger.error("token SignatureException",e);
            return false;
        }
        return false;
    }

}
