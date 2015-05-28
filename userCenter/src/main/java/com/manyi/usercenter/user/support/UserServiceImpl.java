package com.manyi.usercenter.user.support;

import com.manyi.base.entity.Constant;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.message.MessageService;
import com.manyi.usercenter.shiro.util.PasswordHelper;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.bean.*;
import com.manyi.usercenter.user.support.dao.UserRoleDao;
import com.manyi.usercenter.user.support.entity.BaseUser;
import com.manyi.usercenter.user.support.entity.Corporation;
import com.manyi.usercenter.user.support.entity.Individual;
import com.manyi.usercenter.user.support.entity.SysUser;
import com.manyi.usercenter.user.support.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Magic on 2015/1/7.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //private static final Properties properties = ReadPropertiesUtil.readProperties("user.properties");
    //private Properties properties = ReadPropertiesUtil.readProperties("user.properties");

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private MessageService messageService;

    @Override
    public BaseUser getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public List<PlatUser> queryAllSysUsers() {
        return userDao.queryAllSysUsers();
    }

    @Override
    public BaseUser getUserByEtc(String etcNo) {
        return null;
    }

    @Override
    public void addVehicle(VehicleBean vehicleBean) {
        userDao.addVehicle(vehicleBean);
    }

    /**
     * 创建平台用户
     * 1、创建基本用户
     * 2、创建平台用户
     */
    @Override
    public void createSysUser(PlatUser platUser) {

        BaseUser baseUser = new BaseUser();
        baseUser.setLoginName(platUser.getLoginName());
        baseUser.setPassword(platUser.getPassWord());
        baseUser.setType(Constant.PlatUser);
        baseUser.setState(Constant.ENABLE);
        // 存入密钥和密码
        PasswordHelper.encryptPassword(baseUser);
        userDao.createUser(baseUser);

        logger.debug("当前插入的用户id为：" + baseUser.getId());
        System.out.println("当前插入的用户id为：" + baseUser.getId());

        SysUser sysUser = new SysUser();
        sysUser.setUserId(baseUser.getId());
        sysUser.setCreator(1);  // 先写死一个id，之后改成由ShiroUser里获取
        sysUser.setName(platUser.getName());
        userDao.createSysUser(sysUser);
        System.out.println("当前插入的个人用户id为：" + sysUser.getId());
    }

    @Override
    public void updateSysUser(long userId, String state, String name) {

        if (null != state && "" != state) {
            userDao.updateSysUserStatus(userId, state);
        }
        if (null != name && "" != name) {
            userDao.updateSysUserName(userId, name);
        }
    }

    /**
     * 删除平台用户
     * 1、删除基本用户中数据
     * 2、删除平台用户数据
     */
    @Override
    public void deleteSysUser(long userId) {

        userDao.deleteUser(userId);
        userDao.deleteSysUser(userId);
    }

    @Override
    public Individual getIndividualById(Long id) {
        return userDao.getIndividualById(id);
    }

    /**
     * 注册司机用户
     * 1、创建基本用户
     * 2、创建个人用户
     */
    @Override
    public void registerIndividual(UserBean userBean) {

        BaseUser baseUser = new BaseUser();
        baseUser.setLoginName(userBean.getLoginName());
        baseUser.setType(Constant.Individual);
        baseUser.setState(Constant.ENABLE);
        baseUser.setPassword(userBean.getPassWord());
        // 存入密钥和密码
        PasswordHelper.encryptPassword(baseUser);
        userDao.createUser(baseUser);
        System.out.println("baseUser:" + baseUser.getId());

        Individual individual = new Individual();
        individual.setUserId(baseUser.getId());
        individual.setPhone(userBean.getLoginName());
        userDao.createIndividual(individual);
        System.out.println("baseUser:"+individual.getId());

        //增加个人用户角色对应关系
        userRoleDao.addUserRole(Long.valueOf(baseUser.getId()),Long.valueOf(com.manyi.usercenter.util.Constant.INDIVIDUAL.getValue()));
    }

    /**
     * 更新司机用户信息
     * @param individualBean
     */
    @Override
    public void updateIndividual(IndividualBean individualBean) {
        Individual individual = new Individual();
        individual.setBirthDay(individualBean.getBirthDay());
        individual.setDescription(individualBean.getDescription());
        individual.setDriverName(individualBean.getDriverName());
        individual.setHeadPic(individualBean.getHeadPic());
        individual.setIdCardNo(individualBean.getIdCardNo());
        individual.setPhone(individualBean.getPhone());
        individual.setSexual(individualBean.getSexual());
        individual.setUserId(individualBean.getUserId());
        individual.setSecretKey(individualBean.getSecretKey());
        userDao.updateIndividual(individual);
    }

    @Override
    public List<Individual> getIndividual(String loginName) throws BusinessException {
        if (loginName==null||"".equals(loginName)){
            throw new BusinessException(Type.PARA_NULL);
        }
        List<Individual> list = userDao.getIndividual(loginName);
        return list;
    }

    /**
     * 注册企业用户
     * 1、创建基本用户
     * 2、创建企业用户
     */
    @Override
    public void registerCorporation(CorpUser corpUser) {

        BaseUser baseUser = new BaseUser();
        baseUser.setLoginName(corpUser.getLoginName());
        baseUser.setPassword(corpUser.getPassword());
        baseUser.setType(Constant.CorpUser);
        baseUser.setState(Constant.ENABLE);
        PasswordHelper.encryptPassword(baseUser);
        userDao.createUser(baseUser);

        Corporation corporation = new Corporation();
        corporation.setUserId(baseUser.getId());
        userDao.createCorporater(corporation);
    }

    @Override
    public void updateCorporationInfo(Corporation corporation) {
        userDao.updateCorporater(corporation);
    }

    @Override
    public boolean checkCurrentPwd(long userId, String password) {

        BaseUser baseUser = userDao.getBaseUserById(userId);
        String pwd = PasswordHelper.encryptPassword(password, baseUser.getSecretKey());
        boolean result = false;
        if (null != baseUser && null != baseUser.getPassword()) {
            if (pwd.equals(baseUser.getPassword())) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public void updatePassword(String loginName, String password) {
        BaseUser baseUser = new BaseUser();
        baseUser.setLoginName(loginName);
        baseUser.setPassword(password);
        PasswordHelper.encryptPassword(baseUser);

        userDao.updatePassword(baseUser);
    }

    @Override
    public boolean sendMessageCode(String phoneNum,String type,String templateId) throws Exception {
        return messageService.sendMessageCode(phoneNum,type,templateId);
    }

    @Override
    public boolean verifyMessageCode(String phone, String type,String msgCode) throws BusinessException {
        return messageService.isIdentificationCodeValid(phone,type,msgCode);
    }

    @Override
    public boolean sendMegCodeForResetPass(String phoneNum, String type, String templateId) throws Exception {
        return messageService.sendMessageCode(phoneNum,type,templateId);
    }

    @Override
    public boolean verifyMegCodeForResetPass(String phone, String type, String msgCode) throws BusinessException {
        return messageService.isIdentificationCodeValid(phone,type,msgCode);
    }


    @Override
    public Set<String> findRoles(String username) {
        List<String> list=userDao.findRoles(username);
        return new HashSet<String>(list);
    }

    @Override
    public Set<String> findPermissions(String username) {
        List<String> list = userDao.findRoles(username);
        List resultList = new ArrayList();
        for (String rolename:list){
            resultList.addAll(userDao.findPermissions(rolename));
        }
        return new HashSet<String>(resultList);
    }

    @Override
    public void addUserRole(String userId, String roleId) {
        userRoleDao.addUserRole(Long.valueOf(userId),Long.valueOf(roleId));
    }


}
