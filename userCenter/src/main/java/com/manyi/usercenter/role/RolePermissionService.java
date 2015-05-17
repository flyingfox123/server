package com.manyi.usercenter.role;

import com.manyi.usercenter.role.bean.RolePerm;

import java.util.List;

/**
 * Created by zhangyufeng on 2015/5/5 0005.
 */
public interface RolePermissionService {
    /**
     * 绑定角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void bindRolePermissions(Long roleId, Long[] permissionIds);

    /**
     * 解除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void unbindRolePermissions(Long roleId, Long[] permissionIds);

    public List<RolePerm> findRolePermission(Long roleId,Long permissionId);
}
