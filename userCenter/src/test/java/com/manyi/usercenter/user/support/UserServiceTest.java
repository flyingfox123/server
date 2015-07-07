package com.manyi.usercenter.user.support;

import com.manyi.base.entity.Constant;
import com.manyi.base.exception.BusinessException;
import com.manyi.usercenter.permission.PermissionService;
import com.manyi.usercenter.role.RolePermissionService;
import com.manyi.usercenter.role.RoleService;
import com.manyi.usercenter.shiro.util.PasswordHelper;
import com.manyi.usercenter.user.bean.*;
import com.manyi.usercenter.user.support.entity.*;
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
import java.util.ArrayList;
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

    @Resource
    org.apache.shiro.mgt.SecurityManager securityManager;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    BaseUser baseUser=null;
    @Before
    @Rollback(true)
    public void setUp() throws Exception {

        ThreadContext.bind(securityManager);

        UserBean userBean = new UserBean();
        userBean.setLoginName("18853125491");
        userBean.setPassWord("123456");
        userServiceImpl.registerIndividual(userBean);
        this.baseUser = userServiceImpl.getUserByName("18853125491");
        VehicleBean vehicleBean = new VehicleBean();
        vehicleBean.setUserId(this.baseUser.getId());
        vehicleBean.setPlateNo("鲁A123456");
        vehicleBean.setAxlesNum("");
        vehicleBean.setLoadHeight(22.3);
        vehicleBean.setLuCard("128374884959");
        vehicleBean.setOilWear("10.2");
        vehicleBean.setPlateType("卡车");
        vehicleBean.setVehicleBrand("标志");
        vehicleBean.setPlateLength("10*1*2");
        userServiceImpl.addVehicle(vehicleBean);

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
     * 根据etc查询司机信息
     */
    @Test
    @Rollback(true)
    public void getUserByEtcTest(){
        Individual individual = userServiceImpl.getUserByEtc("128374884959");
        assertEquals("18853125491",individual.getLoginName());
    }

    @Test
    @Rollback(true)
    public void getUserAndVehicleInfoTest(){
        PlatUser platUser = new PlatUser();
        platUser.setLoginName("18853125491");
        UserInfo userInfo = userServiceImpl.getUserAndVehicleInfo(platUser);
        assertEquals("18853125491",userInfo.getIndividual().getLoginName());
    }

    @Test
    @Rollback
    public void getUserInfoListTest(){
        PlatUser platUser = new PlatUser();
        platUser.setLoginName("18853125491");
        List<PlatUser> list = new ArrayList<PlatUser>();
        list.add(platUser);
        List<UserVehicle> list1 = userServiceImpl.getUserInfoList(list);
        if (list1.size()>0){
            UserVehicle userVehicle = list1.get(0);
            assertEquals("128374884959",userVehicle.getLuCard());
        }
    }


    /**
     * 企业用户修改个人信息
     */
    @Test
    @Rollback(true)
    public void updateCorporationInfoTest() {
        Corporation corporation = new Corporation();
        corporation.setName("test");
        userServiceImpl.updateCorporationInfo(corporation);

    }

    /**
     * 用户修改密码
     */
    @Test
    @Rollback(true)
    public void updatePasswordtest() {
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
        userBean.setLoginName("18853125482");
        userBean.setPassWord("123456");
        userServiceImpl.registerIndividual(userBean);
        BaseUser baseUser = userServiceImpl.getUserByName("18853125482");
        assertEquals("18853125482",baseUser.getLoginName());
    }

    @Test
    @Rollback(true)
    public void registerCorporationTest(){
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
        String pass = PasswordHelper.encryptPassword("111111","55154216188a16d57b92ec495c1e8740");
        System.out.println(pass);
    }

    @Test
    @Rollback(true)
    public void updateIndividualTest(){
        IndividualBean individualBean = new IndividualBean();
//        individualBean.setUserId(15);
//        individualBean.setDriverName("zhang");
//        individualBean.setSexual("0");
//        individualBean.setPhone("18653125450");
//        individualBean.setIdCardNo("371234568788859740");
//        individualBean.setBirthDay("1986-11-11");
//        individualBean.setSecretKey("12345");
        individualBean.setUserId(baseUser.getId());
        individualBean.setDriverName("zzzzz");
        userServiceImpl.updateIndividual(individualBean);
        List<Individual> individuals = userServiceImpl.findIndividual(individualBean);
        if (individuals.size()>0){
            assertEquals("zzzzz",individuals.get(0).getDriverName());
        }

    }

    @Test
    public void getIndividualTest() throws BusinessException {

        List<Individual> list=userServiceImpl.getIndividual("18853125491");
        Individual individual = list.get(0);
        assertEquals("18853125491",individual.getLoginName());
    }

    @Test
    public void getIndividualExceptionTest() {
        try {
            List<Individual> list=userServiceImpl.getIndividual(null);
            fail("No Exception throw");
        }catch (BusinessException ex){
            assertTrue(ex instanceof BusinessException);
        }
    }

    @Test
    @Rollback(true)
    public void addVehicleTest() throws UnsupportedEncodingException {
//        VehicleBean vehicleBean = new VehicleBean();
//        vehicleBean.setUserId(Long.valueOf("6"));
//        vehicleBean.setPlateNo("鲁A123456");
//        vehicleBean.setAxlesNum("");
//        vehicleBean.setLoadHeight(22.3);
//        vehicleBean.setLuCard("128374884959");
//        vehicleBean.setOilWear("10.2");
//        vehicleBean.setPlateType("卡车");
//        vehicleBean.setVehicleBrand("标志");
//        vehicleBean.setPlateLength("10*1*2");
//        userServiceImpl.addVehicle(vehicleBean);
        VehicleBean vehicleBean = new VehicleBean();
        vehicleBean.setUserId(this.baseUser.getId());
        vehicleBean.setPlateNo("鲁A123456");
        vehicleBean.setAxlesNum("");
        vehicleBean.setLoadHeight(22.3);
        vehicleBean.setLuCard("128374884959");
        vehicleBean.setOilWear("10.2");
        vehicleBean.setPlateType("卡车");
        vehicleBean.setVehicleBrand("标志");
        vehicleBean.setPlateLength("10*1*2");
        userServiceImpl.addVehicle(vehicleBean);

        VehicleBean vehicleBean1 = new VehicleBean();
        vehicleBean1.setLuCard("128374884959");
        List<Vehicle> list = userServiceImpl.findVehicle(vehicleBean1);
        Vehicle vehicle = list.get(0);
        assertEquals("128374884959",vehicle.getLuCard());
    }

    @Test
    public void getIndividualByIdTest(){
        Individual individual = userServiceImpl.getIndividualById(baseUser.getId());
        assertEquals("18853125491",individual.getLoginName());
    }

    @Test
    public void findVehicleByUserId() {
        VehicleBean vehicleBean = new VehicleBean();
        vehicleBean.setUserId(baseUser.getId());
        vehicleBean.setLuCard("128374884959");
        List<Vehicle> list = userServiceImpl.findVehicle(vehicleBean);
        Vehicle vehicle = list.get(0);
        assertEquals("128374884959",vehicle.getLuCard());
    }

    @Test
    public void getUserTest(){
        PlatUser platUser=new PlatUser();
        platUser.setId(67);
        //platUser.setLoginName("test");
        UserInfo userInfo = userServiceImpl.getUserAndVehicleInfo(platUser);
        System.out.println(userInfo);
    }

    @Test
    public void getUserInfoList(){
        List<PlatUser> platUserList = new ArrayList<PlatUser>();
        PlatUser platUser=new PlatUser();
        platUser.setLoginName("test");
        platUser.setName("aaa");
        platUserList.add(platUser);
        platUser=new PlatUser();
        platUser.setLoginName("18654517708");
        platUser.setName("bbb");
        platUserList.add(platUser);
        List<UserVehicle> userInfos = userServiceImpl.getUserInfoList(platUserList);
        System.out.println(userInfos);
    }

    @Test
    @Rollback(true)
    public void updateVehicleTest() {
        VehicleBean vehicleBean = new VehicleBean();
        vehicleBean.setUserId(Long.valueOf("87"));
        vehicleBean.setLuCard("12345678");
        vehicleBean.setPlateNo("123");
        vehicleBean.setVehicleBrand("大众");
        vehicleBean.setLoadHeight(12);
        vehicleBean.setAxlesNum("6");
        vehicleBean.setPlateLength("12*12*12");
        vehicleBean.setOilWear("11");
        userServiceImpl.updateVehicle(vehicleBean);
        List<Vehicle> vehicleList = userServiceImpl.findVehicle(vehicleBean);
        if (vehicleList.size()>0){
            Vehicle vehicle = vehicleList.get(0);
            assertEquals("12345678", vehicle.getLuCard());
            assertEquals("123",vehicle.getPlateNo());
            assertEquals("大众",vehicle.getVehicleBrand());
            assertTrue(12 == vehicle.getLoadHeight());
            assertTrue("6".equals(vehicle.getAxlesNum()));
            assertEquals("12*12*12", vehicle.getPlateLength());
            assertEquals("11",vehicle.getOilWear());
        }
    }

    @Test
    @Rollback(true)
    public void addAddressTest(){
        AddressBean addressBean = new AddressBean();
        addressBean.setUserId(baseUser.getId());
        addressBean.setName("angel");
        addressBean.setMobile("18688812345");
        addressBean.setPostCode("123456");
        addressBean.setProvince("001");
        addressBean.setCity("002");
        addressBean.setArea("003");
        addressBean.setAddress("龙奥北路山东高速大厦");
        addressBean.setFullAddress("山东省济南市高新区龙奥北路山东高速大厦");
        addressBean.setIsDefault('Y');
        addressBean.setRemark("纯粹测试");
        userServiceImpl.addAddress(addressBean);

        List<Address> list = userServiceImpl.findAddress(addressBean);
        Address address = list.get(0);
        assertEquals("angel",address.getName());
    }

    @Test
    @Rollback(true)
    public void findAddressTest(){
        AddressBean addressBean = new AddressBean();
        addressBean.setUserId(baseUser.getId());
        addressBean.setName("angel");
        addressBean.setMobile("18688812345");
        addressBean.setPostCode("123456");
        addressBean.setProvince("001");
        addressBean.setCity("002");
        addressBean.setArea("003");
        addressBean.setAddress("龙奥北路山东高速大厦");
        addressBean.setFullAddress("山东省济南市高新区龙奥北路山东高速大厦");
        addressBean.setIsDefault('Y');
        addressBean.setRemark("纯粹测试");
        userServiceImpl.addAddress(addressBean);

        AddressBean addressBean1 = new AddressBean();
        addressBean1.setUserId(baseUser.getId());

        List<Address> list = userServiceImpl.findAddress(addressBean1);
        Address address = list.get(0);
        assertEquals("angel",address.getName());
    }

    @Test
    @Rollback(true)
    public void updateAddressTest(){
        AddressBean addressBean = new AddressBean();
        addressBean.setUserId(baseUser.getId());
        addressBean.setName("angel");
        addressBean.setMobile("18688812345");
        addressBean.setPostCode("123456");
        addressBean.setProvince("001");
        addressBean.setCity("002");
        addressBean.setArea("003");
        addressBean.setAddress("龙奥北路山东高速大厦");
        addressBean.setFullAddress("山东省济南市高新区龙奥北路山东高速大厦");
        addressBean.setIsDefault('Y');
        addressBean.setRemark("纯粹测试");
        userServiceImpl.addAddress(addressBean);

        AddressBean addressBean1 = new AddressBean();
        addressBean1.setUserId(baseUser.getId());
        List<Address> list1 = userServiceImpl.findAddress(addressBean1);

        Address address = list1.get(0);

        AddressBean addressBean2 = new AddressBean();
        addressBean2.setId(address.getId());

        addressBean2.setName("lucy");
        userServiceImpl.updateAddress(addressBean2);
        List<Address> list2 = userServiceImpl.findAddress(addressBean2);
        Address address2 = list2.get(0);
        assertEquals("lucy",address2.getName());
    }

    @Test
    @Rollback(true)
    public void updateDefAddressTest(){
        AddressBean addressBean = new AddressBean();
        addressBean.setUserId(Long.valueOf("65"));
        addressBean.setId(Long.valueOf("5"));
        userServiceImpl.updateDefAddress(addressBean);
    }

    @Test
    @Rollback(true)
    public void deleteAddressTest(){
        AddressBean addressBean = new AddressBean();
        addressBean.setId(Long.valueOf("8"));
        userServiceImpl.deleteAddress(addressBean);
    }

    @Test
    @Rollback(true)
    public void testFindIndividual(){
        UserBean userBean = new UserBean();
        userBean.setLoginName("13456789090");
        userBean.setPassWord("123789");
        userBean.setSecreKey("342");
        userServiceImpl.registerIndividual(userBean);
        BaseUser baseUser = userServiceImpl.getUserByName("13456789090");
        assertEquals("13456789090",baseUser.getLoginName());
        IndividualBean individualBean = new IndividualBean();
        individualBean.setPhone("13456789090");
        List<Individual> list = userServiceImpl.findIndividual(individualBean);
        Individual Individual = list.get(0);
        assertEquals("13456789090",Individual.getPhone());
    }

    @Test
    @Rollback(true)
    public void testDisableUser(){
        UserBean userBean = new UserBean();
        userBean.setLoginName("13456781212");
        userBean.setPassWord("123789");
        userBean.setSecreKey("342");
        userServiceImpl.registerIndividual(userBean);
        BaseUser baseUser = userServiceImpl.getUserByName("13456781212");
        assertEquals("E",baseUser.getState());
        userServiceImpl.disableUser(baseUser.getId());
        BaseUser baseUser1 = userServiceImpl.getUserByName("13456781212");
        assertEquals("D",baseUser1.getState());
    }

    @Test
    @Rollback(true)
    public void testQueryCorporationInfo(){
//        CorpUser corpUser = new CorpUser();
//        corpUser.setLoginName("18253125492");
//        corpUser.setPassword("123456");
//        userServiceImpl.registerCorporation(corpUser);
//        Corporation corporation = new Corporation();
//        corporation.setPhone("18253125492");
//        List<CorpUser> corpUserrs = userServiceImpl.queryCorporationInfo(corporation);
//        CorpUser corpUser1 = corpUserrs.get(0);
//        assertEquals("18253125492", corpUser1.getLoginName());
    }


}
