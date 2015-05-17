package com.manyi.usercenter.role.bean;

import java.io.Serializable;

/**
 * Created by Magic on 2015/3/2.
 */
public class GrantAuth implements Serializable {
    private static final long serialVersionUID = 1L;

    // 被选中的权限
    private String checkedNodes;

    // 角色id
    private Long roleId;

    public String getCheckedNodes() {
        return checkedNodes;
    }

    public void setCheckedNodes(String checkedNodes) {
        this.checkedNodes = checkedNodes;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
