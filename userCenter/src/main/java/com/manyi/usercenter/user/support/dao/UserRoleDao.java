package com.manyi.usercenter.user.support.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangyufeng on 2015/5/21.
 */
public interface UserRoleDao {

    /**
     * 增加用户角色对应关系
     * @param userId
     * @param roleId
     */
    void addUserRole(@Param("userId")Long userId,@Param("roleId")Long roleId);

    /**
     * 修改用户角色
     * @param id
     * @param userId
     * @param roleId
     */
    void editUserRole(Long id,Long userId,Long roleId);

    /**
     * 删除用户角色
     * @param id
     */
    void deleteUserRole(Long id);

    //List getUserRoleByUserName(String userName);
}
