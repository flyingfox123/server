package com.manyi.usercenter.role.support.dao;

import com.manyi.usercenter.role.bean.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Magic on 2015/1/7.
 */
public interface RoleDao {

    /**
     * 获取所有角色
     * @return
     */
    List<SysRole> getAllRoles();

    /**
     * 根据条件获取角色
     * @param id
     * @param name
     * @return
     */
    List<SysRole> getRoles(@Param("id")String id,@Param("name")String name);

    /**
     * 添加角色
     * @return
     */
    int addRole(SysRole sysrole);

    /**
     * 更新角色
     * @return
     */
    int editRole(SysRole sysrole);

    /**
     * 删除角色
     * @return
     */
    int deleteRole(Long id);

}
