package com.manyi.web.usercenter;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.bean.JsonResult;
import com.manyi.common.bean.request.usercenter.user.MsgCodeContent;
import com.manyi.common.bean.request.usercenter.user.MsgCodeRequestBean;
import com.manyi.common.bean.response.Body;
import com.manyi.common.bean.response.Head;
import com.manyi.common.bean.response.ResponseBean;
import com.manyi.common.util.CommonUtil;
import com.manyi.usercenter.exception.UserGetMsgCodeException;
import com.manyi.usercenter.shiro.util.PasswordHelper;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.bean.MsgCode;
import com.manyi.usercenter.user.bean.PlatUser;
import com.manyi.usercenter.user.support.entity.BaseUser;
import com.manyi.usercenter.user.support.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.ftp.FtpDirEntry;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Magic on 2015/4/15.
 */

@Controller
@RequestMapping("/usercenter")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userservice;

    /**
     * 查询所有系统用户
     * @return
     */
    @RequestMapping("/sysUser/select")
    @ResponseBody
    public List<PlatUser> findSysUser() {
        List<PlatUser> sysSysUser = userservice.queryAllSysUsers();

        return sysSysUser;
    }


    @RequestMapping("/sysUser/insert")
    @ResponseBody
    public ResponseBean sysUserInsert(@RequestBody PlatUser sysUser) {
        ResponseBean responseBean = new ResponseBean();
        Head head = new Head();
        if (null != sysUser) {
            try {
                userservice.createSysUser(sysUser);
                head.setState("success");
            } catch (Exception e) {
                head.setState("fail");
                logger.error("注册系统用户错误", e);
            }
        }
        responseBean.setHead(head);
        return responseBean;
    }



    /**
     * 修改系统用户
     * @param sysUser
     */
    @RequestMapping("/sysUser/update")
    @ResponseBody
    public JsonResult updateSysUser(@Valid @RequestBody SysUser sysUser, Errors errors) {
        if (null != sysUser) {
            if (errors.hasErrors()) {
                return new JsonResult(errors);
            } else {
                try {
//                    userservice.updateSysUser(sysUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return new JsonResult(JsonResult.SUCCESS, "操作成功");
    }

    /**
     * 删除系统用户
     * @param id
     */
    @RequestMapping("/sysUser/delete")
    @ResponseBody
    public JsonResult deleteSysUser(Long id) {
        if (null != id) {
            try {
                userservice.deleteSysUser(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new JsonResult(JsonResult.SUCCESS, "操作成功");
    }


    /**
     * 用户注册发送验证码
     * @param messageCode
     * @return
     * @throws Exception
     */
    @RequestMapping("/sentMessageCode")
    @ResponseBody
    public ResponseBean sendMsgCode(@RequestBody MsgCode messageCode) throws Exception {
        String cellPhone=null;
        if (messageCode!=null){
            cellPhone=messageCode.getPhone();
        }else {
            throw new UserGetMsgCodeException(Type.PARA_NULL);
        }
        if (!CommonUtil.isMobile(cellPhone)){
            throw new UserGetMsgCodeException(Type.PHONEERR_NULL);
        }
        userservice.sendMessageCode(cellPhone,"regesterUser","");
        logger.debug(cellPhone+",发送验证码");
        ResponseBean responseBean = new ResponseBean();
        Head head = new Head();
        head.setState("success");
        head.setDateTime(CommonUtil.getTime("yyyyMMdd HHmmss"));
        responseBean.setHead(head);
        return responseBean;
    }

    /**
     * 注册码验证
     * @param messageCode
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/verifyMsgCode")
    @ResponseBody
    public ResponseBean verifyMsgCode(@RequestBody MsgCode messageCode) throws BusinessException {
        String msgCode=null;
        String phone=null;
        if (messageCode!=null){
            msgCode = messageCode.getMsgCode();
            phone = messageCode.getPhone();
        }else {
            throw new UserGetMsgCodeException(Type.PARA_NULL);
        }

        boolean isSuccess = userservice.verifyMessageCode(phone,"regesterUser",msgCode);
        ResponseBean responseBean = new ResponseBean();
        Head head = new Head();
        if (isSuccess){
            head.setState("success");
        }else {
            head.setState("fail");
            head.setErrMsg("验证码无效");
        }
        head.setDateTime(CommonUtil.getTime("yyyyMMdd HHmmss"));
        responseBean.setHead(head);
        return responseBean;
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public ResponseBean unauthorized(){
        ResponseBean responseBean = new ResponseBean();
        Head head = new Head();
        head.setState("fail");
        head.setErrMsg("无权限访问");
        responseBean.setHead(head);
        return responseBean;
    }

}
