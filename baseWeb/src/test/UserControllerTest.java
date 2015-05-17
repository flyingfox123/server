import com.manyi.base.entity.Constant;
import com.manyi.usercenter.permission.PermissionService;
import com.manyi.usercenter.role.RolePermissionService;
import com.manyi.usercenter.role.RoleService;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.bean.CorpUser;
import com.manyi.usercenter.user.bean.IndivUser;
import com.manyi.usercenter.user.bean.PlatUser;
import com.manyi.usercenter.user.support.UserServiceImpl;
import com.manyi.usercenter.user.support.entity.BaseUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by zhangyufeng on 2015/5/10 0010.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-usercenter-servlet-junit.xml","classpath:spring-baseweb-servlet.xml","classpath:spring-usercenter-shiro-junit.xml"})
@TransactionConfiguration(defaultRollback=false)
public class UserControllerTest {

    @Autowired
    private UserService userServiceImpl;

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
        assertEquals(4, list.size());
    }

    /**
     * 添加平台用户
     */
    @Test
    public void createSysUser() {

        PlatUser platUser = new PlatUser();
        platUser.setLoginName("zhangli");
        platUser.setName("zhangli");
        platUser.setPassword("123456");
        userServiceImpl.createSysUser(platUser);
        BaseUser baseUser = userServiceImpl.getUserByName("zhangli");
        assertEquals("zhangli",baseUser.getLoginName());
    }

    /**
     * 修改平台用户
     */
    @Test
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
    public void deleteSysUser() {
        long userId = 2;
        userServiceImpl.deleteSysUser(userId);
    }

    /**
     * 司机注册
     */
    @Test
    public void registerIndividual() {
        IndivUser indivUser = new IndivUser();

    }

    /**
     * 司机修改个人信息
     */
    @Test
    public void updateIndividualInfo() {
        IndivUser indivUser = new IndivUser();

    }

    /**
     * 企业用户注册
     */
    @Test
    public void registerCorporation() {
        CorpUser corpUser = new CorpUser();

    }

    /**
     * 企业用户修改个人信息
     */
    @Test
    public void updateCorporationInfo() {
        CorpUser corpUser = new CorpUser();

    }

    /**
     * 用户校验当前密码
     */
    @Test
    public void checkCurrentPwd() {
        long userId = 1;
        String password = "123456";
    }

    /**
     * 用户修改密码
     */
    @Test
    public void updatePassword() {
        long userId = 1;
        String password = "123456";

        userServiceImpl.updatePassword(userId, password);
    }

    @Test
    public void testLogin(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("z","123456");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
    }

    /**
     * 断言异常方式1
     */
    @Test
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
    @Test
    public void testLoginFail2() throws IncorrectCredentialsException{

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin","1234567");
        expectedEx.expect(IncorrectCredentialsException.class);
        subject.login(token);

    }

    @Test
    public void checkRolesTest(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());

        subject.checkRole("user");


    }

    @Test
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


    @Test
    public void findPermissionTest(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        subject.checkPermission("user:create");
    }

    @Test
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

}
