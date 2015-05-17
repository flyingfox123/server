package com.manyi.usercenter.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>User: Magic
 * <p>Date: 14-6-20
 * <p>Version: 1.0
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	
	private static Logger logger = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);
	
//    private Cache<String, AtomicInteger> passwordRetryCache;

//    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
//        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
//    }
    public RetryLimitHashedCredentialsMatcher() {
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //retry count + 1
//        AtomicInteger retryCount = passwordRetryCache.get(username);
//        if(retryCount == null) {
//            retryCount = new AtomicInteger(0);
//            passwordRetryCache.put(username, retryCount);
//        }
//        if(retryCount.incrementAndGet() > 5) {
//            //if retry count > 5 throw
//            throw new ExcessiveAttemptsException();
//        }

//        boolean matches = super.doCredentialsMatch(token, info);
        boolean matches = info.equals(token.getPrincipal());
        //return equals(tokenHashedCredentials, accountCredentials);
//        if(matches) {
//            //clear retry count
//            //passwordRetryCache.remove(username);
//        }
        info.getCredentials();
        logger.debug("shiro匹配认证结果："+matches);
        return matches;
    }
}
