package com.manyi.usercenter.user.support.dao;



import com.manyi.usercenter.user.support.entity.SysUser;

import java.util.List;

/**
 * Created by Magic on 2015/1/7.
 */
public interface UserMapper {

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    SysUser findUserByName(String username);

    /**
     * 测试查询
     * @return
     */
    List<SysUser> testSelect();

    /**
     * 测试添加
     * @return
     */
    int testInsert(SysUser sysUser);

    /**
     * 测试更新
     * @return
     */
    int testUpdate(SysUser sysUser);

    /**
     * 测试删除
     * @return
     */
    int testDelete(Long id);
}

