package com.manyi.usercenter.permission;

import com.manyi.usercenter.permission.bean.Permission;

import java.util.List;

/**
 * Created by Magic on 2015/4/11.
 */
public interface PermissionService {
    /**
     * 查询所有权限
     * @return
     */
    List<Permission> findAllPermission();

    /**
     * 查找指定权限
     * @return
     */
    List<Permission> findPermission(String id,String name);

    /**
     * 添加权限
     * @param permission
     * @return
     */
    void addPermission(Permission permission);

    /**
     * 修改资源信息
     * @param permission
     * @return
     */
    void updatePermission(Permission permission);

    /**
     * 删除资源
     * @param id
     * @return
     */
    void deletePermission(Long id);
}
