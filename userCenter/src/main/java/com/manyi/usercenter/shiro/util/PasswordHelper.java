package com.manyi.usercenter.shiro.util;

import com.manyi.usercenter.user.bean.PlatUser;
import com.manyi.usercenter.user.support.entity.BaseUser;
import com.manyi.usercenter.user.support.entity.Password;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 用户密码修改工具类
 * <p>User: Magic
 * <p>Date: 14-6-20
 * <p>Version: 1.0
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "SHA-1";
    private static final int hashIterations = 2;

    /**
     * 为基础用户存入密钥和密码
     * @param baseUser
     */
    public static void encryptPassword(BaseUser baseUser) {

        baseUser.setSecretKey(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                baseUser.getPassword(),
                ByteSource.Util.bytes(baseUser.getSecretKey()),
                hashIterations).toHex();

        baseUser.setPassword(newPassword);
    }

//    public static void encryptPassword(PlatUser baseUser) {
//
//        baseUser.setSecretKey(randomNumberGenerator.nextBytes().toHex());
//
//        String newPassword = new SimpleHash(
//                algorithmName,
//                baseUser.getPassword(),
//                ByteSource.Util.bytes(baseUser.getCredentialsSalt()),
//                hashIterations).toHex();
//
//        baseUser.setPassword(newPassword);
//    }
    /**
     * 根据密钥进行密码加密
     * @param password 密码
     * @param secretKey 密钥
     */
    public static String encryptPassword(String password, String secretKey) {

        String pwd = new SimpleHash(
                algorithmName, password,
                ByteSource.Util.bytes(secretKey),
                hashIterations).toHex();
        return pwd;
    }

}
