package com.manyi.usercenter.token;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.manyi.common.redis.RedisClientTemplate;
import com.manyi.common.util.ReadPropertiesUtil;
import com.manyi.usercenter.user.support.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.*;

/**
 * Created by zhangyufeng on 2015/4/24 0024.
 */
@Component
public class JWTCreater {

    private static final Properties properties = ReadPropertiesUtil.readProperties("user.properties");
    private static final int expireTime = Integer.valueOf(properties.getProperty("tokenexp"));
    private static final int SECONDS=60;
    private static final long MISECOND=1000L;

    @Autowired
    private RedisClientTemplate redisClient;


    /**
     * 创建token
     * @param userName
     * @return
     */
    public String createToken(String userName,String secretKey,String userId){
        HashMap<String,Object> claims = new HashMap<String, Object>();
        long expireTime = getExpireTime();
        claims.put("iss",userName);
        //claims.put("exp",expireTime);
        claims.put("userId",userId);
        claims.put("time", System.currentTimeMillis());
        JWTSigner signer = new JWTSigner(secretKey);
        String token = signer.sign(claims);
        redisClient.set(userId,token);
        return token;
    }

    /**
     * 计算token失效时间，以秒为单位
     * @return
     */
    private long getExpireTime(){
        long curr = System.currentTimeMillis();
        curr+=expireTime*SECONDS*MISECOND;
        return curr/MISECOND;
    }

    /**
     * 创建唯一的tokenId
     * @return
     */
    private String createTokenId(){
        String tokenId = null;
        do {
            tokenId=UUID.randomUUID().toString().replaceAll("-", "");
        }while (redisClient.exists(tokenId));

        return tokenId;
    }

}
