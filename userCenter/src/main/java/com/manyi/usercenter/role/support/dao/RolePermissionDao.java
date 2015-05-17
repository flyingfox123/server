package com.manyi.usercenter.role.support.dao;

import com.manyi.usercenter.role.bean.RolePerm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangyufeng on 2015/5/5 0005.
 */
public interface RolePermissionDao {

    public void bindRolePermissions(@Param("roleId")Long roleId, @Param("permId")Long permissionIds);

    public void unbindRolePermissions(@Param("roleId")Long roleId, @Param("permId")Long permissionIds);

    public List<RolePerm> findRolePermission(@Param("roleId")Long roleId,@Param("permId")Long permissionId);
}
