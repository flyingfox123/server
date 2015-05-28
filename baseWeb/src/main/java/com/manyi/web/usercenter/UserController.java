package com.manyi.web.usercenter;

import com.manyi.base.entity.State;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.bean.JsonResult;
import com.manyi.common.bean.response.ResponseBean;
import com.manyi.common.util.CommonUtil;
import com.manyi.usercenter.exception.UserGetMsgCodeException;
import com.manyi.usercenter.shiro.util.PasswordHelper;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.bean.IndividualBean;
import com.manyi.usercenter.user.bean.MsgCode;
import com.manyi.usercenter.user.bean.PlatUser;
import com.manyi.usercenter.user.bean.UserBean;
import com.manyi.usercenter.user.support.entity.BaseUser;
import com.manyi.usercenter.user.support.entity.Individual;
import com.manyi.usercenter.util.Constant;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    public ResponseBean sysUserInsert(@RequestBody PlatUser sysUser) throws BusinessException {
        ResponseBean responseBean = new ResponseBean();
        if (null != sysUser) {
            if (!CommonUtil.isPassWord(sysUser.getPassWord())){
                throw new BusinessException(Type.PASSWORD_WRONG);
            }
            userservice.createSysUser(sysUser);
        }else {
            throw new BusinessException(Type.PARA_NULL);
        }
        responseBean.setState(State.SUCCESS.getString());
        return responseBean;
    }

    @RequestMapping("/anon/individual/insert")
    @ResponseBody
    public ResponseBean registerIndividual(@RequestBody UserBean userBean) throws BusinessException {
        ResponseBean responseBean = new ResponseBean();
        if (null != userBean) {
            if (!CommonUtil.isPassWord(userBean.getPassWord())){
                throw new BusinessException(Type.PASSWORD_WRONG);
            }
            userservice.registerIndividual(userBean);
        }else {
            throw new BusinessException(Type.PARA_NULL);
        }
        responseBean.setState(State.SUCCESS.getString());
        return responseBean;
    }


    /**
     * 修改系统用户
     * @param sysUser
     */
    @RequestMapping("/sysUser/update")
    @ResponseBody
    public JsonResult updateSysUser(@RequestBody PlatUser sysUser) {
        if (null != sysUser) {
            try {
               // userservice.updateSysUser(sysUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new JsonResult(JsonResult.SUCCESS, "操作成功");
    }

    /**
     * 更新修改司机用户信息
     * @param individualBean
     * @return
     */
    @RequestMapping("/auth/individual/update")
    @ResponseBody
    public ResponseBean updateIndividual(@RequestBody IndividualBean individualBean) throws BusinessException {
        ResponseBean responseBean = new ResponseBean();
        if (null != individualBean) {
            userservice.updateIndividual(individualBean);
        }else {
            throw new BusinessException(Type.PARA_NULL);
        }
        responseBean.setState(State.SUCCESS.getString());
        return responseBean;
    }

    /**
     * 用户找回密码重设密码操作
     * @param sysUser
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/sysUser/anon/updateSysUserPassWord")
    @ResponseBody
    public ResponseBean updateSysUserPassWord(@RequestBody PlatUser sysUser) throws BusinessException {
        ResponseBean responseBean = new ResponseBean();
        if (null != sysUser) {
            if (!CommonUtil.isPassWord(sysUser.getPassWord())){
                throw new BusinessException(Type.PASSWORD_WRONG);
            }
            userservice.updatePassword(sysUser.getLoginName(), sysUser.getPassWord());
        }else {
            throw new BusinessException(Type.PARA_NULL);
        }
        responseBean.setState(State.SUCCESS.getString());
        return responseBean;
    }

    /**
     * 系统用户重设密码
     * @param sysUser
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/auth/resetSysUserPassWord")
    @ResponseBody
    public ResponseBean resetUserPassWord(@RequestBody PlatUser sysUser) throws BusinessException {
        ResponseBean responseBean = new ResponseBean();
        if (sysUser==null){
            throw new BusinessException(Type.PARA_NULL);
        }
        if (sysUser.getLoginName()==null||sysUser.getPassWord()==null){
            throw new BusinessException(Type.PARA_NULL);
        }
        if (!CommonUtil.isPassWord(sysUser.getPassWord())){
            throw new BusinessException(Type.PASSWORD_WRONG);
        }
        String loginName = sysUser.getLoginName();
        String oldPassWord = sysUser.getOldPassWord();
        BaseUser baseUser = userservice.getUserByName(loginName);
        String secNewPassWord = PasswordHelper.encryptPassword(sysUser.getPassWord(),baseUser.getSecretKey());
        String secOldPassWord = PasswordHelper.encryptPassword(oldPassWord, baseUser.getSecretKey());
        if (!secOldPassWord.equals(baseUser.getPassword())){
            responseBean.setState(State.FAIL.getString());
            responseBean.setErrMsg("原密码不正确");
            return responseBean;
        }
        if (secNewPassWord.equals(secOldPassWord)){
            responseBean.setState(State.FAIL.getString());
            responseBean.setErrMsg("新密码不能与旧密码相同");
            return responseBean;
        }
        if (null != sysUser) {
            userservice.updatePassword(sysUser.getLoginName(), sysUser.getPassWord());
        }else {
            throw new BusinessException(Type.PARA_NULL);
        }
        responseBean.setState(State.SUCCESS.getString());
        return responseBean;
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
        ResponseBean responseBean = new ResponseBean();
        String cellPhone=null;
        BaseUser baseUser = userservice.getUserByName(messageCode.getPhone());
        if (baseUser!=null){
            responseBean.setState(State.FAIL.getString());
            responseBean.setErrMsg("用户已存在");
            return responseBean;
        }
        if (messageCode!=null){
            cellPhone=messageCode.getPhone();
        }else {
            throw new UserGetMsgCodeException(Type.PARA_NULL);
        }
        if (!CommonUtil.isMobile(cellPhone)){
            throw new UserGetMsgCodeException(Type.PHONEERR_NULL);
        }
        boolean isSuccess = userservice.sendMessageCode(cellPhone, Constant.REGESTERUSER.getValue(), Constant.REGISTERCODE.getValue());
        if (isSuccess){
            responseBean.setState(State.SUCCESS.getString());
            logger.debug(cellPhone+",发送验证码成功");
        }else {
            responseBean.setState(State.FAIL.getString());
            logger.debug(cellPhone+",发送验证码失败");
        }

        responseBean.setDateTime(CommonUtil.getTime("yyyyMMdd HHmmss"));
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

        boolean isSuccess = userservice.verifyMessageCode(phone,Constant.REGESTERUSER.getValue(),msgCode);
        ResponseBean responseBean = new ResponseBean();
        if (isSuccess){
            responseBean.setState(State.SUCCESS.getString());
        }else {
            responseBean.setState(State.FAIL.getString());
            responseBean.setErrMsg("验证码无效");
        }
        responseBean.setDateTime(CommonUtil.getTime("yyyyMMdd HHmmss"));
        return responseBean;
    }

    /**
     * 找回密码功能发送验证码
     * @param messageCode
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendMegCodeForResetPass")
    @ResponseBody
    public ResponseBean sendMegCodeForResetPass(@RequestBody MsgCode messageCode) throws Exception {
        String cellPhone=null;
        ResponseBean responseBean = new ResponseBean();
        BaseUser baseUser = userservice.getUserByName(messageCode.getPhone());
        if (baseUser==null){
            responseBean.setState(State.FAIL.getString());
            responseBean.setErrMsg("用户不存在");
            return responseBean;
        }

        if (messageCode!=null){
            cellPhone=messageCode.getPhone();
        }else {
            throw new UserGetMsgCodeException(Type.PARA_NULL);
        }
        if (!CommonUtil.isMobile(cellPhone)){
            throw new UserGetMsgCodeException(Type.PHONEERR_NULL);
        }
        boolean isSuccess = userservice.sendMegCodeForResetPass(cellPhone, Constant.RESETPASS.getValue(),Constant.FINDPASSWORD.getValue());
        if (isSuccess){
            responseBean.setState(State.SUCCESS.getString());
            logger.debug(cellPhone + ",发送验证码成功");
        }else {
            responseBean.setState(State.FAIL.getString());
            logger.error(cellPhone + ",发送验证码失败");
        }

        responseBean.setDateTime(CommonUtil.getTime("yyyyMMdd HHmmss"));
        return responseBean;
    }

    /**
     * 找回密码功能校验验证码
     * @param messageCode
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/verifyMegCodeForResetPass")
    @ResponseBody
    public ResponseBean verifyMegCodeForResetPass(@RequestBody MsgCode messageCode) throws BusinessException {
        String msgCode=null;
        String phone=null;
        if (messageCode!=null){
            msgCode = messageCode.getMsgCode();
            phone = messageCode.getPhone();
        }else {
            throw new UserGetMsgCodeException(Type.PARA_NULL);
        }

        boolean isSuccess = userservice.verifyMegCodeForResetPass(phone, Constant.RESETPASS.getValue(), msgCode);
        ResponseBean responseBean = new ResponseBean();
        if (isSuccess){
            responseBean.setState(State.SUCCESS.getString());
        }else {
            responseBean.setState(State.FAIL.getString());
            responseBean.setErrMsg("验证码无效");
        }
        responseBean.setDateTime(CommonUtil.getTime("yyyyMMdd HHmmss"));
        return responseBean;
    }

    /**
     * 司机上传头像
     * @param request
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/auth/individual/headPicUpload")
    @ResponseBody
    public ResponseBean headPicUpload(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws Exception {
        String userId = request.getParameter("userId");
        ResponseBean responseBean = new ResponseBean();
        if(file.isEmpty()){
            responseBean.setState(State.FAIL.getString());
            responseBean.setErrMsg("上传文件为空");
            logger.error("上传文件为空");
            return responseBean;
        }
        String fileName=file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!("jpg".equals(ext)||"png".equals(ext)||"gif".equals(ext)||"bmp".equals(ext)||"jpeg".equals(ext))){
            throw new BusinessException(Type.NOTPIC);
        }

        //上传文件的物理路径.../baseWeb/pic/hedPic/userId/xxx.jpg
        String uploadPath=request.getSession().getServletContext().getRealPath(Constant.PICDIR.getValue() + "//" + userId + "//" +Constant.HEADPICDIR.getValue());

        File uploadDir=new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        File uploadFile=new File(uploadPath + "/" + userId+"."+ext);
        file.transferTo(uploadFile);
        logger.debug("userId:" + userId + ",上传头像成功");
        responseBean.setState(State.SUCCESS.getString());
        String headPicUrl=CommonUtil.getBasePath(request)+"/"+Constant.PICDIR.getValue() + "/" + userId + "/" +Constant.HEADPICDIR.getValue()+"/"+ userId+"."+ext;
        IndividualBean individualBean = new IndividualBean();
        individualBean.setUserId(Long.valueOf(userId));
        individualBean.setHeadPic("/"+Constant.PICDIR.getValue() + "/" + userId + "/" +Constant.HEADPICDIR.getValue()+"/"+ userId+"."+ext);
        userservice.updateIndividual(individualBean);
        responseBean.setBody(headPicUrl);
        return responseBean;
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException {
        String path="D:\\worktool\\apache-tomcat-7.0.57\\webapps\\baseWeb\\pic\\63\\headPic\\63.jpg";
        File file=new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName=new String("63.jpg".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    @RequestMapping("/auth/individual/getIndividual")
    @ResponseBody
    public ResponseBean getIndividual(HttpServletRequest request,@RequestBody UserBean userBean) throws BusinessException {
        String loginName = userBean.getLoginName();
        ResponseBean responseBean = new ResponseBean();
        List<Individual> individualList = userservice.getIndividual(loginName);
        if (individualList.size()>1){
            responseBean.setState(State.FAIL.getString());
            responseBean.setErrMsg("用户存在多个");
            return responseBean;
        }
        Individual individual = individualList.get(0);
        individual.setHeadPic(CommonUtil.getBasePath(request)+individual.getHeadPic());
        responseBean.setBody(individual);
        return responseBean;
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public ResponseBean unauthorized(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(State.FAIL.getString());
        responseBean.setErrMsg("无权限访问");
        return responseBean;
    }

    @RequestMapping("/reLogin")
    @ResponseBody
    public ResponseBean reLogin(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(State.FAIL.getString());
        responseBean.setErrMsg("账户从其它设备登录");
        return responseBean;
    }

}
