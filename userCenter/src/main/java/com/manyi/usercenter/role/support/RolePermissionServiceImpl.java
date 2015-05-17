package com.manyi.usercenter.role.support;

import com.manyi.usercenter.role.RolePermissionService;
import com.manyi.usercenter.role.bean.RolePerm;
import com.manyi.usercenter.role.support.dao.RolePermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyufeng on 2015/5/5 0005.
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    RolePermissionDao rolePermissionDao;

    @Override
    public void bindRolePermissions(Long roleId, Long[] permissionIds) {
        if(permissionIds==null||permissionIds.length==0){
            return;
        }
        for (Long permissionId:permissionIds){
            if(findRolePermission(roleId, permissionId).size()<=0){
                rolePermissionDao.bindRolePermissions(roleId,permissionId);
            }
        }

    }

    @Override
    public void unbindRolePermissions(Long roleId, Long[] permissionIds) {
        if(permissionIds==null||permissionIds.length==0||roleId==null){
            return;
        }
        for (Long permissionId:permissionIds) {
            rolePermissionDao.unbindRolePermissions(roleId,permissionId);
        }
    }

    @Override
    public List<RolePerm> findRolePermission(Long roleId, Long permissionId) {
        return rolePermissionDao.findRolePermission(roleId,permissionId);
    }
}
