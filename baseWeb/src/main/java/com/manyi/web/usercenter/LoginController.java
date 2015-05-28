package com.manyi.web.usercenter;

import com.manyi.base.entity.Type;
import com.manyi.bean.JsonResult;
import com.manyi.common.bean.response.ResponseBean;
import com.manyi.common.util.CommonUtil;
import com.manyi.usercenter.exception.UserLoginException;
import com.manyi.usercenter.shiro.util.PasswordHelper;
import com.manyi.usercenter.token.JWTCreater;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.bean.UserBean;
import com.manyi.usercenter.user.support.entity.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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

    /**
     * 用户登录
     * @param userBean
     * @return
     * @throws UserLoginException
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseBean login(@RequestBody UserBean userBean) throws UserLoginException {
        ResponseBean responseBean = new ResponseBean();
        String loginName =null;
        String password=null;

        if (userBean !=null){
            loginName = userBean.getLoginName();
            password = userBean.getPassWord();
        }else{
            throw new UserLoginException(Type.DEFAULT_ERROR);
        }

        if(null != loginName && loginName != "" && null != password && password != "") {
            BaseUser baseUser1 = userService.getUserByName(loginName);
            String secretKey=null;
            if (baseUser1!=null){
                secretKey = baseUser1.getSecretKey();
            }else{
                responseBean.setState(JsonResult.FAILURE);
                responseBean.setErrMsg("用户不存在~");
                return responseBean;
            }
            String loginPassword = PasswordHelper.encryptPassword(password, secretKey);
            boolean isPassWordCorrect = loginPassword.equals(baseUser1.getPassword());
            //UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, password);
            //Subject subject = SecurityUtils.getSubject();

                //subject.login(usernamePasswordToken);
            if (isPassWordCorrect) {
                String token = jwtCreater.createToken(loginName,baseUser1.getSecretKey(),String.valueOf(baseUser1.getId()));
                responseBean.setState(JsonResult.SUCCESS);
                responseBean.setToken(token);
                responseBean.setDateTime(CommonUtil.getTime("yyyyMMdd HHmmss"));
                return responseBean;
            }else{
                responseBean.setState(JsonResult.FAILURE);
                responseBean.setErrMsg("密码错误");
                return responseBean;
            }

        } else {
            responseBean.setState(JsonResult.FAILURE);
            responseBean.setErrMsg("登录失败，账号或密码为空");
            return responseBean;
        }
    }

}
