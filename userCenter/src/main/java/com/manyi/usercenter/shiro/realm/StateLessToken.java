package com.manyi.usercenter.shiro.realm;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by zhangyufeng on 2015/5/14 0014.
 */
public class StateLessToken implements AuthenticationToken {
    private String username;
    private String token;

    public StateLessToken(String username,String token){
        this.username=username;
        this.token=token;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
