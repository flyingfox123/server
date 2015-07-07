package com.manyi.usercenter.user.support;

import com.manyi.base.entity.Constant;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.message.MessageService;
import com.manyi.usercenter.event.RegisterSuccessEvent;
import com.manyi.usercenter.shiro.util.PasswordHelper;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.bean.*;
import com.manyi.usercenter.user.support.dao.UserRoleDao;
import com.manyi.usercenter.user.support.entity.*;
import com.manyi.usercenter.user.support.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    public static final char ISDEFAULT = 'Y';
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public BaseUser getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public List<PlatUser> queryAllSysUsers() {
        return userDao.queryAllSysUsers();
    }

    @Override
    public List<PlatUser> querySysUser(PlatUser platUser) {

        if(null != platUser && 0 != platUser.getPageSize() &&platUser.getPageNum()>0)
        {
            platUser.setPageNum((platUser.getPageNum()-1)*platUser.getPageSize());
        }

        return userDao.querySysUser(platUser);
    }

    @Override
    public int querySysUserCount(PlatUser platUser) {
        return userDao.querySysUserCount(platUser);
    }

    @Override
    public Individual getUserByEtc(String etcNo) {
        VehicleBean vehicleBean = new VehicleBean();
        vehicleBean.setLuCard(etcNo);
        List<Vehicle> vehicleList = userDao.findVehicle(vehicleBean);
        if (vehicleList.size()>0){
            Vehicle vehicle = vehicleList.get(0);
            List<Individual> individuals = userDao.getIndividual(vehicle.getLoginName());
            if (individuals.size()>0){
                return individuals.get(0);
            }
        }
        return null;
    }

    @Override
    public UserInfo getUserAndVehicleInfo(PlatUser platUser) {
        UserInfo userInfo = new UserInfo();
        BaseUser baseUser = userDao.getUser(platUser);
        if (baseUser!=null){
            userInfo.setId(baseUser.getId());
            userInfo.setState(baseUser.getState());
            userInfo.setCreateTime(baseUser.getCreateTime());
            userInfo.setLoginName(baseUser.getLoginName());
            userInfo.setType(baseUser.getType());
            long id = baseUser.getId();
            if (Constant.Individual.equals(baseUser.getType())){
                Individual individual = userDao.getIndividualById(baseUser.getId());
                userInfo.setIndividual(individual);
            }
            VehicleBean vehicleBean = new VehicleBean();
            vehicleBean.setUserId(id);
            List<Vehicle> vehicleList = userDao.findVehicle(vehicleBean);
            if (vehicleList.size()>0){
                userInfo.setVehicle(vehicleList.get(0));
            }
        }

        return userInfo;
    }

    @Override
    public List<UserVehicle> getUserInfoList(List<PlatUser> platUserList) {
        List<UserVehicle> infoList = new ArrayList<UserVehicle>();
        for (PlatUser platUser:platUserList){
            UserVehicle userVehicle = new UserVehicle();
            userVehicle.setLoginName(platUser.getLoginName());
            userVehicle.setName(platUser.getName());
            BaseUser baseUser = null;
            if (platUser.getLoginName()!=null&&!"".equals(platUser.getLoginName())){
                baseUser = userDao.getUser(platUser);
            }

            if (baseUser!=null){
                long id = baseUser.getId();
                VehicleBean vehicleBean = new VehicleBean();
                vehicleBean.setUserId(id);
                List<Vehicle> vehicleList = userDao.findVehicle(vehicleBean);
                if (vehicleList.size()>0){
                    Vehicle vehicle=vehicleList.get(0);
                    if (vehicle.getPlateNo()!=null&&!"".equals(vehicle.getPlateNo())){
                        userVehicle.setLuCard(vehicle.getLuCard());
                        userVehicle.setPlateNo(vehicle.getPlateNo());
                        infoList.add(userVehicle);
                    }
                }
            }
        }
        return infoList;
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
        System.out.println("当前插入的平台用户id为：" + sysUser.getId());
    }

    @Override
    public void updateSysUser(long userId, String state, String name) {

//        if (null != state && "" != state) {
//            userDao.updateSysUserStatus(userId, state);
//        }
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

        //注册车辆信息
        VehicleBean vehicleBean = new VehicleBean();
        vehicleBean.setUserId(baseUser.getId());
        userDao.addVehicle(vehicleBean);
        applicationContext.publishEvent(new RegisterSuccessEvent(baseUser));
    }

    /**
     *查询司机用户 zhaoyuxin
     * @param individualBean
     * @return
     */
    public List<Individual> findIndividual(IndividualBean individualBean){
        if(null != individualBean && 0 != individualBean.getPageSize() &&individualBean.getPageNum()>0)
        {
            individualBean.setPageNum((individualBean.getPageNum()-1)*individualBean.getPageSize());
        }
        List<Individual> individual =  userDao.findIndividual(individualBean);
        return individual;
    }

    /**
     *查询司机用户数量 zhaoyuxin
     * @param individualBean
     * @return
     */
    public int findIndividualCount(IndividualBean individualBean){
        int count =  userDao.findIndividualCount(individualBean);
        return count;
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

    public  List<CorpUser> queryCorporationInfo(Corporation corporation){
        if(null != corporation && 0 != corporation.getPageSize() &&corporation.getPageNum()>0)
        {
            corporation.setPageNum((corporation.getPageNum()-1)*corporation.getPageSize());
        }
        List<CorpUser> corpUsers = userDao.queryCorporationInfo(corporation);
        return corpUsers;
    }

    public  int queryCorporationCount(Corporation corporation){
        return  userDao.queryCorporationCount(corporation);
    }

    @Override
    public boolean checkCurrentPwd(long userId, String password) {

        BaseUser baseUser = userDao.getBaseUserById(userId);
        if (baseUser==null){
            return false;
        }
        String pwd = PasswordHelper.encryptPassword(password, baseUser.getSecretKey());
        boolean result = false;
        if (null != baseUser.getPassword()) {
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

    @Override
    public List<Vehicle> findVehicle(VehicleBean vehicleBean) {
        List<Vehicle> vehicleList = userDao.findVehicle(vehicleBean);
        return vehicleList;
    }

    @Override
    public void updateVehicle(VehicleBean vehicleBean) {
        userDao.updateVehicle(vehicleBean);
    }

    @Override
    public void addAddress(AddressBean addressBean) {
        userDao.addAddress(addressBean);
    }

    @Override
    public List<Address> findAddress(AddressBean addressBean) {
        List<Address> addressList = userDao.findAddress(addressBean);
        return addressList;
    }

    @Override
    public void updateAddress(AddressBean addressBean) {
        userDao.updateAddress(addressBean);
    }

    @Override
    public void deleteAddress(AddressBean addressBean) {
        userDao.deleteAddress(addressBean);
    }

    @Override
    public void updateDefAddress(AddressBean addressBean) {
        userDao.setAllAddressNoDef(addressBean);
        addressBean.setIsDefault(ISDEFAULT);
        userDao.updateAddress(addressBean);
    }

    public void disableUser(long userId){
        userDao.updateSysUserStatus(userId, Constant.DISABLE );
    }

    /**
     * 通过id查询基础用户
     * @param userId
     * @return
     */
    public BaseUser findBaseUserById(long userId) {
        return userDao.getBaseUserById(userId);
    }
}
