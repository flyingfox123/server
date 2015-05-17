package com.manyi.usercenter.permission.support;

import com.manyi.usercenter.permission.bean.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by zhangyufeng on 2015/5/4 0004.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-usercenter-servlet.xml","classpath:spring-usercenter-shfiro-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class PermissionServiceTest {
    @Autowired
    private PermissionServiceImpl permissionService;

    @Test
    public void findPermissionTest(){
        List<Permission> list = permissionService.findPermission("3", null);

        List<Permission> list1 = permissionService.findPermission(null,"user");
        for (Permission p:list){
            assertEquals("3",p.getId());
        }
        for (Permission p:list1){
            boolean a = p.getName().indexOf("user")>=0;
            assertTrue(a);
        }
    }

    @Test
    public void addPermissionTest(){
        Permission permission = new Permission();
        permission.setName("user:create1");
        permission.setCode("用户创建1");
        permission.setDescription("用户模块新增");
        permission.setUri("/permission/test/createuser");
        permission.setIsLeaf("y");
        permission.setLevel(Long.valueOf(1));
        permission.setState("E");
        permissionService.addPermission(permission);

        List<Permission> list= permissionService.findPermission(null, "user:create1");
        for (Permission p : list){
            assertEquals("user:create1",p.getName());
        }
    }

    @Test
    public void updatePermissionTest(){
        Permission permission = new Permission();
        permission.setId("3");
        permission.setIsLeaf("n");
        permission.setCode("用户创建1");
        permissionService.updatePermission(permission);
    }

    @Test
    public void deletePermissionTest(){
        permissionService.deletePermission(Long.valueOf(4));
        List<Permission> list = permissionService.findPermission("4", null);
        assertEquals(0,list.size());
    }
}
