package com.manyi.usercenter.role.support;

import com.manyi.usercenter.role.RolePermissionService;
import com.manyi.usercenter.role.RoleService;
import com.manyi.usercenter.role.bean.RolePerm;
import com.manyi.usercenter.role.bean.SysRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by zhangyufeng on 2015/5/5 0005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-usercenter-servlet.xml","classpath:spring-usercenter-shiro-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class RolePermissionServiceTest {

    @Autowired
    private RolePermissionService roleService;

    @Test
    @Rollback(false)
    public void bindRolePermissionsTest(){
        roleService.bindRolePermissions(Long.valueOf(10), new Long[]{Long.valueOf(5),Long.valueOf(4)});
    }

    @Test
    @Rollback(false)
    public void unbindRolePermissions(){
        roleService.unbindRolePermissions(Long.valueOf(10), new Long[]{Long.valueOf(5),Long.valueOf(4)});
    }

    @Test
    public void findRolePermissionTest(){
        List<RolePerm> list = roleService.findRolePermission(Long.valueOf(10), Long.valueOf(5));
        System.out.println(list.size());
    }
}
