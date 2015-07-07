package com.manyi.usercenter.user;

import com.manyi.base.exception.BusinessException;
import com.manyi.usercenter.user.bean.*;
import com.manyi.usercenter.user.support.entity.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Magic on 2015/1/7.
 */
public interface UserService {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    BaseUser getUserByName(String username);

    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    Individual getIndividualById(Long id);

    /**
     * 通过etc卡号查询用户信息
     * @param etcNo
     * @return
     */
    Individual getUserByEtc(String etcNo);

    /**
     * 根据用户基础信息查询用户详细信息，包括地址信息，车辆信息
     * @param platUser
     * @return
     */
    UserInfo getUserAndVehicleInfo(PlatUser platUser);

    /**
     * 获取用户信息列表
     * @param platUserList
     * @return
     */
    List<UserVehicle> getUserInfoList(List<PlatUser> platUserList);

    /**
     * 查询所有平台用户
     * @return
     */
    List<PlatUser> queryAllSysUsers();

    /**
     * 查询平台用户
     * @return
     */
    List<PlatUser> querySysUser(PlatUser platUser);

    /**
     * 查询平台用户数量
     * @return
     */
    int querySysUserCount(PlatUser platUser);
    /**
     * 添加平台用户
     * @param platUser
     * @return
     */
    void createSysUser(PlatUser platUser) throws BusinessException;

    /**
     * 修改平台用户
     * @param userId
     * @param status
     * @param name
     * @return
     */
    void updateSysUser(long userId, String status, String name);


    /**
     * 删除平台用户
     * @param id
     * @return
     */
    void deleteSysUser(long id);

    /**
     * 司机注册
     * @param userBean
     */
    void registerIndividual(UserBean userBean);
    /**
     *查询司机用户
     * @param individualBean
     * @return
     */
    List<Individual> findIndividual(IndividualBean individualBean);
    /**
     *查询司机用户数量
     * @param individualBean
     * @return
     */
    int findIndividualCount(IndividualBean individualBean);

    /**
     * 修改司机用户
     */
    void updateIndividual(IndividualBean individualBean);

    /**
     * 获取司机信息通过登录名
     * @param loginName
     * @return
     */
    public List<Individual> getIndividual(String loginName) throws BusinessException;

    /**
     * 企业用户注册
     * @param corpUser
     */
    void registerCorporation(CorpUser corpUser);

    /**
     * 企业用户修改个人信息
     * @param corporation
     */
    void updateCorporationInfo(Corporation corporation);

    /**
     * 用户校验当前密码
     */
    boolean checkCurrentPwd(long userId, String password);

    /**
     * 用户修改密码
     * @param loginName
     * @param password
     */
    void updatePassword(String loginName, String password);

    /**
     * 用户注册发送验证码
     * @param phoneNum
     * @param type
     * @param templateId
     */
    boolean sendMessageCode(String phoneNum,String type,String templateId) throws Exception;

    /**
     * 用户注册校验验证码
     * @param phone
     * @param type
     * @param msgCode
     * @return
     * @throws BusinessException
     */
    boolean verifyMessageCode(String phone,String type,String msgCode) throws BusinessException;

    /**
     * 用户找回密码发送验证码
     * @param phoneNum
     * @param type
     * @param templateId
     * @throws Exception
     */
    boolean sendMegCodeForResetPass(String phoneNum,String type,String templateId) throws Exception;

    /**
     * 用户找回密码
     * @param phone
     * @param type
     * @param msgCode
     * @return
     * @throws BusinessException
     */
    boolean verifyMegCodeForResetPass(String phone,String type,String msgCode) throws BusinessException;


    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据角色查询其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

    /**
     * 增加用户角色对应关系
     * @param userId
     * @param roleId
     */
    void addUserRole(String userId,String roleId);

    /**
     * 增加车辆信息
     * @param vehicleBean
     */
    void addVehicle(VehicleBean vehicleBean);

    /**
     * 查询车辆信息
     * @param vehicleBean
     * @return
     */
    List<Vehicle> findVehicle(VehicleBean vehicleBean) throws BusinessException;

    /**
     * 更新修改车辆信息
     * @param vehicleBean
     */
    void updateVehicle(VehicleBean vehicleBean);

    /**
     * 增加地址信息
     * @param addressBean
     */
    void addAddress(AddressBean addressBean);

    /**
     * 查找地址信息
     * @param addressBean
     * @return
     */
    List<Address> findAddress(AddressBean addressBean);

    /**
     * 更新修改地址信息
     * @param addressBean
     */
    void updateAddress(AddressBean addressBean);

    /**
     * 删除地址
     * @param addressBean
     */
    void deleteAddress(AddressBean addressBean);

    /**
     * 修改默认地址
     * @param addressBean
     */
    void updateDefAddress(AddressBean addressBean);

    /**
     * 停用用户
     * @param userId
     */
    void disableUser(long userId);

    /**
     * 查询企业用户
     * @param corporation
     * @return
     */
    List<CorpUser> queryCorporationInfo(Corporation corporation);

    /**
     * 查询企业用户数量
     * @param corporation
     * @return
     */
    int queryCorporationCount(Corporation corporation);

    /**
     * 通过id查询基础用户
     * @param userId
     * @return
     */
    BaseUser findBaseUserById(long userId);
}
