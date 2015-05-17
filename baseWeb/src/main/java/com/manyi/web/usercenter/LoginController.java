package com.manyi.web.usercenter;

import com.manyi.base.entity.Message;
import com.manyi.base.entity.Type;
import com.manyi.bean.JsonResult;
import com.manyi.common.bean.request.usercenter.LoginDataContent;
import com.manyi.common.bean.request.usercenter.LoginRequestBean;
import com.manyi.common.bean.response.Head;
import com.manyi.common.bean.response.ResponseBean;
import com.manyi.usercenter.exception.UserLoginException;
import com.manyi.usercenter.shiro.util.PasswordHelper;
import com.manyi.usercenter.token.JWTCreater;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.bean.User;
import com.manyi.usercenter.user.support.entity.BaseUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Magic on 2015/1/29.
 */
@Controller
@RequestMapping("/usercenter")
public class LoginController {

    @Autowired
    private JWTCreater jwtCreater;

    @Autowired
    private UserService userService;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 用户登录
     * @param user
     * @return
     * @throws UserLoginException
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseBean login(@RequestBody User user) throws UserLoginException {
        ResponseBean responseBean = new ResponseBean();
        Head head = new Head();
        String loginName =null;
        String password=null;

        if (user!=null){
            loginName =user.getLoginName();
            password = user.getPassWord();
        }else{
            throw new UserLoginException(Type.DEFAULT_ERROR);
        }

        if(null != loginName && loginName != "" && null != password && password != "") {
            BaseUser baseUser1 = userService.getUserByName(loginName);
            String secretKey=null;
            if (baseUser1!=null){
                secretKey = baseUser1.getSecretKey();
            }else{
                head.setState(JsonResult.FAILURE);
                head.setErrMsg("用户或密码不存在~");
                responseBean.setHead(head);
                return responseBean;
            }
            String loginPassword = PasswordHelper.encryptPassword(password, secretKey);
            boolean isPassWordCorrect = loginPassword.equals(baseUser1.getPassword());
            //UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, password);
            //Subject subject = SecurityUtils.getSubject();

                //subject.login(usernamePasswordToken);
            if (isPassWordCorrect) {
                //BaseUser baseUser = userService.getUserByName(loginName);
                String token = jwtCreater.createToken(loginName,baseUser1.getSecretKey(),String.valueOf(baseUser1.getId()));
                head.setState(JsonResult.SUCCESS);
                head.setToken(token);
                Date date = new Date();
                head.setDateTime(simpleDateFormat.format(date));
                responseBean.setHead(head);
                return responseBean;
            }else{
                head.setState(JsonResult.FAILURE);
                head.setErrMsg("密码错误");
                responseBean.setHead(head);
                return responseBean;
            }

        } else {
            head.setState(JsonResult.FAILURE);
            head.setErrMsg("登录失败，账号或密码为空");
            responseBean.setHead(head);
            return responseBean;
        }
    }

}
