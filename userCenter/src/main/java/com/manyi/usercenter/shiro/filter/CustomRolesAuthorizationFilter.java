package com.manyi.usercenter.shiro.filter;

import com.manyi.usercenter.token.JWTValidate;
import com.manyi.usercenter.util.Constant;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangyufeng on 2015/5/13 0013.
 */
public class CustomRolesAuthorizationFilter extends AuthorizationFilter {

    @Autowired
    private JWTValidate jwtValidate;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String token = httpServletRequest.getHeader(Constant.TOKEN.getValue());
        String loginName = httpServletRequest.getHeader(Constant.LOGINNAME.getValue());
        // 校验token是否失效，如果失效则自动生成新的token,不需要重新登录
       boolean newToken = jwtValidate.validate(token,loginName);
//        if (newToken!=null){
//            httpServletRequest.setAttribute("newToken",newToken);
//        }
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
}
