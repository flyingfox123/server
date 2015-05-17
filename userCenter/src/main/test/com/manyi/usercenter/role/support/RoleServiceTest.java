package com.manyi.usercenter.role.support;

import com.manyi.usercenter.role.RoleService;
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

import static org.junit.Assert.*;
/**
 * Created by zhangyufeng on 2015/5/5 0005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-usercenter-servlet.xml","classpath:spring-usercenter-shiro-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void findRolesTest(){
        List<SysRole> list = roleService.findRoles("1", null);
        assertEquals(Long.valueOf(1), list.get(0).getId());

        List<SysRole> list1 = roleService.findRoles(null, "管理员");
        for (SysRole sysRole:list1){
            boolean boo = sysRole.getName().indexOf("管理员")>=0;
            assertTrue(boo);
        }
    }

    @Test
    public void findAllRolesTest(){
        List<SysRole> list = roleService.findAllRoles();
        System.out.println(list.size());
    }

    @Test
    @Rollback(true)
    public void addRoleTest(){
        SysRole sysRole = new SysRole();
        sysRole.setName("user");
        sysRole.setState("1");
        sysRole.setCreator("administrator");
        roleService.addRole(sysRole);

        List<SysRole> list = roleService.findRoles(null, "user");
        assertEquals(2,list.size());
    }

    @Test
    @Rollback(true)
    public void editRoleTest(){
        SysRole sysRole = new SysRole();
        sysRole.setId(Long.valueOf(10));
        sysRole.setModifier("zhang");
        sysRole.setState("5");
        roleService.editRole(sysRole);

        List<SysRole> list = roleService.findRoles("10", null);
        assertEquals("5",list.get(0).getState());
    }

    @Test
    @Rollback(true)
    public void deleteRoleTest(){
        roleService.deleteRole(Long.valueOf(10));
        List<SysRole> list = roleService.findRoles("10", null);
        assertEquals(0,list.size());
    }
}
