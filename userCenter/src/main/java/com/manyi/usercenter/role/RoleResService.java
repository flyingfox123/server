package com.manyi.usercenter.role;

import com.manyi.usercenter.role.bean.GrantAuth;
import com.manyi.usercenter.role.bean.RoleResourceTreeNode;

import java.util.List;
import java.util.Set;

/**
 * Created by Magic on 2015/2/27.
 */

public interface RoleResService {

    /**
     * 根据角色ID获取该角色的权限id
     *
     * @param roleId 角色ID
     * @return Set<String> 资源ID集合
     */
    public Set<String> findByRoleId(Long roleId);

    /**
     * 根据角色ID获取该角色的权限
     * @param roleId
     * @return
     */
    public List<RoleResourceTreeNode> findAllRoleResoByRoleId(Long roleId);

    /**
     * 为当前角色授权
     * @param grantAuth
     */
    boolean grantAuth(GrantAuth grantAuth);
}
