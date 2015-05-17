package com.manyi.usercenter.user.support.dao;

import com.manyi.usercenter.user.bean.PlatUser;
import com.manyi.usercenter.user.support.entity.BaseUser;
import com.manyi.usercenter.user.support.entity.Corporation;
import com.manyi.usercenter.user.support.entity.Individual;
import com.manyi.usercenter.user.support.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Magic on 2015/1/7.
 */
public interface UserDao {

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
     * 创建基础用户
     * @param baseUser
     */
    int createUser(BaseUser baseUser);

    /**
     * 添加平台用户
     * @param sysUser
     * @return
     */
    int createSysUser(SysUser sysUser);

    /**
     * 修改平台用户状态
     * @return
     */
    int updateSysUserStatus(@Param("userId") long userId, @Param("state") String state);

    /**
     * 修改平台用户昵称
     * @param userId
     * @param name
     */
    void updateSysUserName(@Param("userId")long userId, @Param("name")String name);

    /**
     * 删除基本用户
     * @param id
     * @return
     */
    int deleteUser(Long id);

    /**
     * 删除平台用户
     * @return
     */
    int deleteSysUser(Long id);

    /**
     * 注册个体用户
     * @param individual
     */
    void createIndividual(Individual individual);

    /**
     * 修改个人信息
     * @param individual
     */
    void updateIndividual(Individual individual);

    /**
     * 创建企业用户
     * @param corporation
     */
    void createCorporater(Corporation corporation);

    /**
     * 修改企业用户信息
     * @param corporation
     */
    void updateCorporater(Corporation corporation);

    /**
     * 修改用户密码
     * @param baseUser
     */
    void updatePassword(BaseUser baseUser);

    /**
     * 根据Id获取当前基础用户信息
     * @param userId
     * @return
     */
    BaseUser getBaseUserById(long userId);

    /**
     * 查询用户角色
     * @param name
     * @return
     */
    List<String> findRoles(@Param("name")String name);

    /**
     * 查询用户权限
     * @param rolename
     * @return
     */
    List<String> findPermissions(@Param("name")String rolename);
}
