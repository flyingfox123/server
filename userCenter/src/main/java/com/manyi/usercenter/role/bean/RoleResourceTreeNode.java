package com.manyi.usercenter.role.bean;

/**
 * Created by Magic on 2015/2/27.
 * 权限树的展现实体
 */
public class RoleResourceTreeNode {

    /**
     * 树ID
     */
    private String id;

    /**
     * 树根节点ID
     */
    private String pId;

    /**
     * 节点名称对应角色名称
     */
    private String name;

    /**
     * 是否选中状态
     */
    private Boolean checked;

    /**
     * 节点是否打开状态
     */
    private Boolean open;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 定义子节点字符串
     */
    private String children;

    /**
     * 定义是否可编辑
     */
    private Boolean chkDisabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public Boolean getChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(Boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }
}
