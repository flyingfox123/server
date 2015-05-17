package com.manyi.usercenter.role.support;

import com.manyi.usercenter.role.RoleService;
import com.manyi.usercenter.role.bean.SysRole;
import com.manyi.usercenter.role.support.dao.RoleDao;
import com.manyi.usercenter.shiro.util.CurrentUserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* Created by Magic on 2015/1/7.
*/
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<SysRole> findAllRoles() {
        List<SysRole> list = roleDao.getAllRoles();
        return list;
    }

    @Override
    public List<SysRole> findRoles(String id, String name) {
        return roleDao.getRoles(id,name);
    }

    @Override
    public boolean addRole(SysRole sysRole) {
        // 获取当前登录的用户id
        //Long userId = CurrentUserUtils.getCurrentUserId();
        Date date = new Date();

        //sysRole.setCreator(userId);
        sysRole.setCreateTime(date);
        //sysRole.setModifier(userId);
        sysRole.setModifyTime(date);

        int val = roleDao.addRole(sysRole);
        if (val > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editRole(SysRole sysRole) {
        // 获取当前登录的用户id
//        Long userId = CurrentUserUtils.getCurrentUserId();
//        Date date = new Date();
//
//        //sysRole.setModifier(userId);
//        sysRole.setModifyTime(date);

        int val = roleDao.editRole(sysRole);
        if (val > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRole(Long id) {
        int val = roleDao.deleteRole(id);
        if (val > 0) {
            return true;
        }
        return false;
    }



}
