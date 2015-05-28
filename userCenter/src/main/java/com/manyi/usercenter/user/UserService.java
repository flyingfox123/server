package com.manyi.usercenter.user;

import com.manyi.base.exception.BusinessException;
import com.manyi.usercenter.user.bean.*;
import com.manyi.usercenter.user.support.entity.BaseUser;
import com.manyi.usercenter.user.support.entity.Corporation;
import com.manyi.usercenter.user.support.entity.Individual;

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
    BaseUser getUserByEtc(String etcNo);

    /**
     * 查询所有平台用户
     * @return
     */
    List<PlatUser> queryAllSysUsers();

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

    void addVehicle(VehicleBean vehicleBean);
}
