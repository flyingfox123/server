package com.manyi.usercenter.shiro.filter;
import com.manyi.usercenter.shiro.realm.StateLessToken;
import com.manyi.usercenter.token.JWTValidate;
import com.manyi.usercenter.util.Constant;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyufeng on 2015/5/14 0014.
 */
public class StatelessAuthcFilter extends AuthorizationFilter {

    private final static Logger logger = LoggerFactory.getLogger(StatelessAuthcFilter.class);

    @Autowired
    private JWTValidate jwtValidate;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;

        String username = httpServletRequest.getHeader(Constant.LOGINNAME.getValue());

        String token = httpServletRequest.getHeader(Constant.TOKEN.getValue());
        logger.debug("发送的token:"+token);

        //生成无状态Token
        StateLessToken stateLessToken = new StateLessToken(username, token);

        try {
            //委托给Realm进行登录
            getSubject(request, response).login(stateLessToken);
        } catch (Exception e) {
            logger.debug("模拟登录失败："+e.getMessage());
            e.printStackTrace();
            //onLoginFail(response); //6、登录失败
            return false;
        }

        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0)
        {
            return true;
        }
        for(int i=0;i<rolesArray.length;i++)
        {
            if(subject.hasRole(rolesArray[i]))
            {
                return true;
            }
        }
        logger.debug(username+",的角色无权限访问请求的url,");
        return false;
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }
}


