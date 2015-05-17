package com.manyi.usercenter.shiro.filter;
import com.manyi.usercenter.shiro.realm.StateLessToken;
import com.manyi.usercenter.token.JWTValidate;
import com.manyi.usercenter.util.Constant;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
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
public class StatelessAuthcFilter extends AccessControlFilter {

    @Autowired
    private JWTValidate jwtValidate;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;


        String username = httpServletRequest.getHeader(Constant.LOGINNAME.getValue());

        String token = httpServletRequest.getHeader(Constant.TOKEN.getValue());

        //生成无状态Token
        StateLessToken stateLessToken = new StateLessToken(username, token);

        try {
            //委托给Realm进行登录
            getSubject(request, response).login(stateLessToken);
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(response); //6、登录失败
            return false;
        }

        boolean isValidate = jwtValidate.validate(token,username);
//        if (newToken!=null){
//            httpServletRequest.setAttribute("newToken",newToken);
//            httpServletResponse.setHeader("token",newToken);
//        }
        if (!isValidate){
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
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;

        String username = httpServletRequest.getHeader(Constant.LOGINNAME.getValue());

        String token = httpServletRequest.getHeader(Constant.TOKEN.getValue());

        //生成无状态Token
        StateLessToken stateLessToken = new StateLessToken(username, token);

        try {
            //委托给Realm进行登录
            getSubject(request, response).login(stateLessToken);
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(response); //6、登录失败
            return false;
        }
        return true;
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }
}


