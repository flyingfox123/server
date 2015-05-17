package com.manyi.usercenter.user.support;

import com.manyi.base.entity.Constant;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.message.MessageService;
import com.manyi.common.util.RandomUtil;
import com.manyi.common.util.ReadPropertiesUtil;
import com.manyi.usercenter.shiro.util.PasswordHelper;
import com.manyi.usercenter.shiro.util.ShiroUser;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.bean.CorpUser;
import com.manyi.usercenter.user.bean.IndivUser;
import com.manyi.usercenter.user.bean.PlatUser;
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
    private MessageService messageService;

    @Override
    public BaseUser getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public List<PlatUser> queryAllSysUsers() {
        return userDao.queryAllSysUsers();
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

        System.out.println(sysUser.getId());
    }

    public static void main(String args[]){
        BaseUser baseUser=new BaseUser();
        baseUser.setLoginName("admin");
        baseUser.setPassword("123456");
        baseUser.setSecretKey("df9579fbb577baeff08052de67a8ce86");
        PasswordHelper.encryptPassword(baseUser);
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

    /**
     * 注册个人用户
     * 1、创建基本用户
     * 2、创建个人用户
     */
    @Override
    public void registerIndividual(IndivUser indivUser) {

        Date date = new Date();

        BaseUser baseUser = new BaseUser();
        baseUser.setLoginName(indivUser.getLoginName());
        baseUser.setCreateTime(date);
        baseUser.setType(Constant.PlatUser);
        baseUser.setState(Constant.ENABLE);
        // 存入密钥和密码
        PasswordHelper.encryptPassword(baseUser);
        userDao.createUser(baseUser);

        Individual individual = new Individual();
        individual.setDescription(indivUser.getDescription());
        individual.setDriverName(indivUser.getDriverName());
        individual.setExpectDestination(indivUser.getExpectDestination());
        individual.setIdCardNo(indivUser.getIdCardNo());
        individual.setOwner(indivUser.getOwner());
        individual.setOwnerMobile(indivUser.getOwnerMobile());
        individual.setPlateLength(indivUser.getPlateLength());
        individual.setPlateLoad(indivUser.getPlateLoad());
        individual.setPlateNo(indivUser.getPlateNo());
        individual.setPlateSerialNo(indivUser.getPlateSerialNo());
        individual.setPlateType(indivUser.getPlateType());
        individual.setUpdateTime(date);
        individual.setUserId(baseUser.getId());
        userDao.createIndividual(individual);
    }

    @Override
    public void updateIndividualInfo(Individual individual) {
        individual.setUpdateTime(new Date());
        userDao.updateIndividual(individual);
    }

    /**
     * 注册企业用户
     * 1、创建基本用户
     * 2、创建企业用户
     */
    @Override
    public void registerCorporation(CorpUser corpUser) {

        Date date = new Date();

        BaseUser baseUser = new BaseUser();
        baseUser.setLoginName(corpUser.getLoginName());
        baseUser.setCreateTime(date);
        baseUser.setType(Constant.PlatUser);
        baseUser.setState(Constant.ENABLE);
        // 存入密钥和密码
        PasswordHelper.encryptPassword(baseUser);
        userDao.createUser(baseUser);

        Corporation corporation = new Corporation();
        corporation.setEmail(corpUser.getEmail());
        corporation.setLegalPerson(corpUser.getLegalPerson());
        corporation.setName(corpUser.getName());
        corporation.setPhone(corpUser.getPhone());
        corporation.setRemark(corpUser.getRemark());
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
    public void updatePassword(long userId, String password) {
        BaseUser baseUser = new BaseUser();
        baseUser.setId(userId);
        baseUser.setPassword(password);
        PasswordHelper.encryptPassword(baseUser);
        userDao.updatePassword(baseUser);
    }

    @Override
    public void sendMessageCode(String phoneNum,String type,String templateId) throws Exception {
        messageService.sendMessageCode(phoneNum,type,templateId);
    }

    @Override
    public boolean verifyMessageCode(String phone, String type,String msgCode) throws BusinessException {
        boolean isSuccess = messageService.isIdentificationCodeValid(phone,type,msgCode);
        return isSuccess;
    }

    @Override
    public boolean isEffective(String code) {
        //从缓存中取出验证码，及发送时间，检查时间是否过期
        String sendTime = "12423243";
        String effectiveTime = "";//(String)properties.get("failturetime");
        long min = Long.valueOf(effectiveTime);
        long mm=min*60*1000;
        if((System.currentTimeMillis()-Long.valueOf(sendTime))<mm){
            return true;
        }
        return false;
    }

    @Override
    public String createToke(String deviceId, String userName) {
        return null;
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


}
