package com.manyi.usercenter.user.support;

import com.manyi.base.entity.Constant;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.util.CommonUtil;
import com.manyi.usercenter.permission.PermissionService;
import com.manyi.usercenter.role.RolePermissionService;
import com.manyi.usercenter.role.RoleService;
import com.manyi.usercenter.shiro.util.PasswordHelper;
import com.manyi.usercenter.user.bean.*;
import com.manyi.usercenter.user.support.entity.BaseUser;
import com.manyi.usercenter.user.support.entity.Individual;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by Magic on 2015/1/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-usercenter-servlet-junit.xml","classpath:spring-usercenter-shiro-junit.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Resource
    org.apache.shiro.mgt.SecurityManager securityManager;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() throws Exception {

        ThreadContext.bind(securityManager);

    }


    /**
     * 通过用户名获取用户
     */
    @Test
    public void getUserByName() {
        String username = "admin";
        BaseUser baseUser = userServiceImpl.getUserByName(username);
        assertEquals("admin",baseUser.getLoginName());
    }

    /**
     * 查询所有平台用户
     */
    @Test
    public void queryAllSysUsers() {
        List<PlatUser> list = userServiceImpl.queryAllSysUsers();
        assertTrue(list.size()>0);
    }

    /**
     * 添加平台用户
     */
    @Test
    @Rollback(true)
    public void createSysUser() {

        PlatUser platUser = new PlatUser();
        platUser.setLoginName("13953188888");
        platUser.setName("13953188888");
        platUser.setPassword("123456");
        userServiceImpl.createSysUser(platUser);
        BaseUser baseUser = userServiceImpl.getUserByName("13953188888");
        assertEquals("13953188888",baseUser.getLoginName());
    }


    /**
     * 修改平台用户
     */
    //@Test
    //@Rollback(true)
    public void updateSysUser() {
        long userId = 5;
        String state = Constant.DISABLE;
        String name = "嘿嘿小明";
        userServiceImpl.updateSysUser(userId, state, name);
    }

    /**
     * 删除平台用户
     */
    @Test
    @Rollback(true)
    public void deleteSysUser() {
        long userId = 9;
        userServiceImpl.deleteSysUser(userId);
        BaseUser baseUser = userServiceImpl.getUserByName("13112345678");
        assertNull(baseUser);
    }

    /**
     * 司机注册
     */
    //@Test
    public void registerIndividual() {
        IndividualBean individualBean = new IndividualBean();

    }

    /**
     * 司机修改个人信息
     */
    //@Test
    public void updateIndividualInfo() {
        IndividualBean individualBean = new IndividualBean();

    }


    /**
     * 企业用户修改个人信息
     */
    //@Test
    public void updateCorporationInfo() {
        CorpUser corpUser = new CorpUser();

    }

    /**
     * 用户修改密码
     */
    @Test
    @Rollback(true)
    public void updatePassword() {
        String loginName = "admin";
        String password = "123456";

        userServiceImpl.updatePassword(loginName, password);
    }


    /**
     * 断言异常方式1
     */
    //@Test
    public void testLoginFail(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin","1234567");
        try {
            subject.login(token);
            fail("No Exception thrown.");
        } catch (Exception e){
            assertTrue(e instanceof IncorrectCredentialsException);
        }
    }

    /**
     * 断言异常常用方式2
     * @throws IncorrectCredentialsException
     */
   // @Test
    public void testLoginFail2() throws IncorrectCredentialsException{

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin","1234567");
        expectedEx.expect(IncorrectCredentialsException.class);
        subject.login(token);

    }

   //@Test
    public void checkRolesTest(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());

        subject.checkRole("user");


    }

    //@Test
    public void checkRolesFailTest(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        try {
            subject.checkRole("userNone");
            fail("No Exception thrown.");
        }catch (Exception e){
            assertTrue(e instanceof UnauthorizedException);
        }
    }


    //@Test
    public void findPermissionTest(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        subject.checkPermission("user:create");
    }

    //@Test
    public void findPermissionFailTest(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        try {
            subject.checkPermission("user:createNone");
            fail("No Exception thrown.");
        }catch (Exception e){
            assertTrue(e instanceof UnauthorizedException);
        }

    }

    @Test
    @Rollback(true)
    public void registerIndividualTest(){
        UserBean userBean = new UserBean();
        userBean.setLoginName("18253125491");
        userBean.setPassWord("123456");
        userServiceImpl.registerIndividual(userBean);
        BaseUser baseUser = userServiceImpl.getUserByName("18253125491");
        assertEquals("18253125491",baseUser.getLoginName());
    }

    @Test
    @Rollback(true)
    public void registerCorporation(){
        CorpUser corpUser = new CorpUser();
        corpUser.setLoginName("18253125492");
        corpUser.setPassword("123456");
        userServiceImpl.registerCorporation(corpUser);
        BaseUser baseUser = userServiceImpl.getUserByName("18253125492");
        assertEquals("18253125492",baseUser.getLoginName());
    }

    @Test
    @Rollback(true)
    public void addUserRoleTest(){

        userServiceImpl.addUserRole("9","10");
    }

    @Test
    public void passWordHelperTest(){
        String pass = PasswordHelper.encryptPassword("111111","7f30641157c142e8615860342d443c02");
        System.out.println(pass);
    }

    @Test
    @Rollback(false)
    public void updateIndividualTest(){
        IndividualBean individualBean = new IndividualBean();
        individualBean.setUserId(15);
        individualBean.setDriverName("zhang");
        individualBean.setSexual("0");
        individualBean.setPhone("18653125450");
        individualBean.setIdCardNo("371234568788859740");
        individualBean.setBirthDay("1986-11-11");
        individualBean.setSecretKey("12345");
        userServiceImpl.updateIndividual(individualBean);
    }

    @Test
    public void getIndividualTest() throws BusinessException {

        List<Individual> list=userServiceImpl.getIndividual("test");
        Individual individual = list.get(0);
        System.out.println(list);
    }

    @Test
    @Rollback(false)
    public void addVehicleTest() throws UnsupportedEncodingException {
        VehicleBean vehicleBean = new VehicleBean();
        vehicleBean.setUserId(Long.valueOf("6"));
        vehicleBean.setPlateNo("鲁A123456");
        vehicleBean.setAxlesNum(4);
        vehicleBean.setLoadHeight(22.3);
        vehicleBean.setLuCard("128374884959");
        vehicleBean.setOilWear("10.2");
        vehicleBean.setPlateType("卡车");
        vehicleBean.setVehicleBrand("标志");
        vehicleBean.setPlateLength("10*1*2");
        userServiceImpl.addVehicle(vehicleBean);
    }
}
