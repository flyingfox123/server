package com.manyi.usercenter.role;

import com.manyi.usercenter.role.bean.SysRole;

import java.util.List;

/**
 * Created by Magic on 2015/2/5.
 */
public interface RoleService {

    /**
     * 获取所有角色
     * @return
     */
    List<SysRole> findAllRoles();

    /**
     * 根据条件获取角色
     * @return
     */
    List<SysRole> findRoles(String id,String name);

    /**
     * 添加角色
     * @param sysrole
     * @return
     */
    boolean addRole(SysRole sysrole);

    /**
     * 修改角色信息
     * @param sysrole
     * @return
     */
    boolean editRole(SysRole sysrole);

    /**
     * 删除角色
     * @param id
     * @return
     */
    boolean deleteRole(Long id);



}
