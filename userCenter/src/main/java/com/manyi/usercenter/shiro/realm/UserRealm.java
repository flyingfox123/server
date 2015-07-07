package com.manyi.usercenter.shiro.realm;

import com.manyi.usercenter.token.JWTValidate;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.support.entity.BaseUser;
import com.manyi.usercenter.permission.PermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private JWTValidate jwtValidate;

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StateLessToken;
    }
    /**
     * 登录过滤器，为用户授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //String username = (String) principals.fromRealm(getName()).iterator().next();
        String username = (String)principals.getPrimaryPrincipal();
        if( username != null ){
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            // 查询用户授权信息
            authorizationInfo.setRoles(userService.findRoles(username));
            authorizationInfo.setStringPermissions(userService.findPermissions(username));

            return authorizationInfo;
        }

        return null;
    }
    /**
     * 登录过滤器，验证用户,获取认证信息
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//
//        // 用户名获取的第一种方式
//        String username = (String) authcToken.getPrincipal();
//
//        if (username!=null&&!"".equals(username)){
//            BaseUser baseUser = userService.getUserByName(username);
//            if (baseUser!=null){
//                //ShiroUser shiroUser = new ShiroUser(baseUser.getId(), baseUser.getLoginName());
//                return new SimpleAuthenticationInfo(baseUser.getLoginName(), baseUser.getPassword(), ByteSource.Util.bytes(baseUser
//                        .getSecretKey()), getName());
//            }else {
//                throw new UnknownAccountException();
//            }
//        }
//        return null;
//    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StateLessToken statelessToken = (StateLessToken) authenticationToken;
        String username = statelessToken.getUsername();
        String token = statelessToken.getToken();

        return new SimpleAuthenticationInfo(
                username,
                token,
                getName());
    }
}