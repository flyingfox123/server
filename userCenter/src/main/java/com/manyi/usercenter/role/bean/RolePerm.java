package com.manyi.usercenter.role.bean;

/**
 * Created by zhangyufeng on 2015/5/5 0005.
 */
public class RolePerm {
    private String id;
    private String permId;
    private String roleId;

//    private String name; //url名称/描述
//    private String url; //地址
//    private String roles; //所需要的角色，可省略
//    private String permissions; //所需要的权限，可省略

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getRoles() {
//        return roles;
//    }
//
//    public void setRoles(String roles) {
//        this.roles = roles;
//    }
//
//    public String getPermissions() {
//        return permissions;
//    }
//
//    public void setPermissions(String permissions) {
//        this.permissions = permissions;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
