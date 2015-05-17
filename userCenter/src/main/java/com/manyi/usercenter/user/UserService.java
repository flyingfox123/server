package com.manyi.usercenter.user;

import com.manyi.base.exception.BusinessException;
import com.manyi.usercenter.user.bean.CorpUser;
import com.manyi.usercenter.user.bean.IndivUser;
import com.manyi.usercenter.user.bean.PlatUser;
import com.manyi.usercenter.user.support.entity.BaseUser;
import com.manyi.usercenter.user.support.entity.Corporation;
import com.manyi.usercenter.user.support.entity.Individual;
import com.manyi.usercenter.user.support.entity.SysUser;

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
     * 查询所有平台用户
     * @return
     */
    List<PlatUser> queryAllSysUsers();

    /**
     * 添加平台用户
     * @param platUser
     * @return
     */
    void createSysUser(PlatUser platUser);

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
     * @param indivUser
     */
    void registerIndividual(IndivUser indivUser);

    /**
     * 司机修改个人信息
     * @param individual
     */
    void updateIndividualInfo(Individual individual);

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
     * @param userId
     * @param password
     */
    void updatePassword(long userId, String password);

    /**
     * 用户注册发送验证码
     * @param phoneNum
     * @param type
     * @param templateId
     */
    void sendMessageCode(String phoneNum,String type,String templateId) throws Exception;

    boolean verifyMessageCode(String phone,String type,String msgCode) throws BusinessException;

    /**
     * 校验验证码
     * @param code
     * @return
     */
    boolean isEffective(String code);

    /**
     * 创建
     * @param deviceId
     * @param userName
     * @return
     */
    String createToke(String deviceId,String userName);

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
}
