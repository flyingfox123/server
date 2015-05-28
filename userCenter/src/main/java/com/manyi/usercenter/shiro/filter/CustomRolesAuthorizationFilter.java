package com.manyi.usercenter.shiro.filter;

import com.manyi.usercenter.token.JWTValidate;
import com.manyi.usercenter.util.Constant;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangyufeng on 2015/5/13 0013.
 */
public class CustomRolesAuthorizationFilter extends AccessControlFilter {

    private final static Logger logger = LoggerFactory.getLogger(StatelessAuthcFilter.class);

    @Autowired
    private JWTValidate jwtValidate;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String token = httpServletRequest.getHeader(Constant.TOKEN.getValue());
        String loginName = httpServletRequest.getHeader(Constant.LOGINNAME.getValue());
        // 校验token是否失效，如果失效则自动生成新的token,不需要重新登录
       boolean isValidate = jwtValidate.validate(token,loginName);
       logger.debug("token验证结果："+isValidate);

        return isValidate;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        // If the subject isn't identified, redirect to login URL
        if (subject.getPrincipal() == null) {
            saveRequestAndRedirectToLogin(request, response);
        } else {
            // If subject is known but not authorized, redirect to the unauthorized URL if there is one
            // If no unauthorized URL is specified, just return an unauthorized HTTP status code
            String loginUrl = getLoginUrl();
            //SHIRO-142 - ensure that redirect _or_ error code occurs - both cannot happen due to response commit:
            if (StringUtils.hasText(loginUrl)) {
                WebUtils.issueRedirect(request, response, loginUrl);
            } else {
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return false;
    }
}
