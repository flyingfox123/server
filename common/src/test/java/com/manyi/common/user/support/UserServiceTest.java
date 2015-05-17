package com.manyi.common.user.support;

/*import com.manyi.base.entity.Constant;
import com.manyi.common.shiro.util.PasswordHelper;
import com.manyi.common.user.bean.CorpUser;
import com.manyi.common.user.bean.IndivUser;
import com.manyi.common.user.bean.PlatUser;
import com.manyi.common.user.support.dao.UserDao;
import com.manyi.common.user.support.entity.BaseUser;
import com.manyi.common.user.support.entity.Corporation;
import com.manyi.common.user.support.entity.Individual;
import com.manyi.common.user.support.entity.SysUser;
*/
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2015/1/7.

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-common-servlet.xml"})
@TransactionConfiguration(defaultRollback=false)
@Transactional*/
public class UserServiceTest {

//    @Autowired
//    private UserServiceImpl userServiceImpl;

//    @Before
//    public void setUp() throws Exception {
//
//        ApplicationContext aCtx = new FileSystemXmlApplicationContext(
//                "classpath:spring-common-servlet.xml");
//        this.userServiceImpl = (UserServiceImpl) aCtx
//                .getBean("userService");
//    }
//
    @After
    public void tearDown() throws Exception {

    }

    /**
     * 通过用户名获取用户
     */
    @Test
    public void getUserByName() {
//        String username = "admin";
//        BaseUser baseUser = userServiceImpl.getUserByName(username);
//        System.out.println("基础用户id=" + baseUser.getId());
    }

    /**
     * 查询所有平台用户
     */
    @Test
    public void queryAllSysUsers() {
//        List<PlatUser> list = userServiceImpl.queryAllSysUsers();
//        for (PlatUser pu : list) {
//            System.out.println(pu.getUserId() + "--" + pu.getName());
//        }
    }

    /**
     * 添加平台用户
     */
    @Test
    public void createSysUser() {

//        PlatUser platUser = new PlatUser();
//        platUser.setLoginName("xiaoming2");
//        platUser.setName("小明2");
//        platUser.setPassword("123456");
//        userServiceImpl.createSysUser(platUser);
    }

    /**
     * 修改平台用户
     */
    @Test
    public void updateSysUser() {
//        long userId = 6;
//        String state = Constant.DISABLE;
//        String name = "嘿嘿是小明";
//        userServiceImpl.updateSysUser(userId, state, name);
    }

    /**
     * 删除平台用户
     */
    @Test
    public void deleteSysUser() {
        long userId = 4;
//        userServiceImpl.deleteSysUser(userId);
    }

    /**
     * 司机注册
     */
    @Test
    public void registerIndividual() {
//        IndivUser indivUser = new IndivUser();
//        indivUser.setLoginName("13112345678");
//        indivUser.setPassword("123456");
//        userServiceImpl.registerIndividual(indivUser);
    }

    /**
     * 司机修改个人信息
     */
    @Test
    public void updateIndividualInfo() {
//        Individual individual = new Individual();
//        individual.setUserId(9);
//        individual.setDriverName("司机");
//        individual.setDescription("描述");
//        individual.setExpectDestination("西方极乐"); // 期望地
//        individual.setIdCardNo("130108");
//        individual.setOwner("小米"); // 车主
//        individual.setOwnerMobile("13112345678");
//        individual.setPlateLength(11d); // 车长
//        individual.setPlateLoad(10d); // 负载
//        individual.setPlateNo("鲁A8888"); // 车牌号
//        individual.setPlateSerialNo("A123"); // 车架号
//        individual.setPlateType("大车");
//        userServiceImpl.updateIndividualInfo(individual);
    }

    /**
     * 企业用户注册
     */
    @Test
    public void registerCorporation() {
//        CorpUser corpUser = new CorpUser();
//        corpUser.setLoginName("13838381438");
//        corpUser.setPassword("123456");
//        userServiceImpl.registerCorporation(corpUser);

    }

    /**
     * 企业用户修改个人信息
     */
    @Test
    public void updateCorporationInfo() {
//        Corporation corporation = new Corporation();
//        corporation.setEmail("aaa@163.com");
//        corporation.setLegalPerson("魔杰克"); // 法人
//        corporation.setName("企业名称");
//        corporation.setPhone("13145678912");
//        corporation.setNickName("哈哈"); // 别名
//        corporation.setRemark("");
//        userServiceImpl.updateCorporationInfo(corporation);

    }

    /**
     * 用户校验当前密码
     */
    @Test
    public void checkCurrentPwd() {
        long userId = 7;
        String password = "1237536";
//        boolean boo = userServiceImpl.checkCurrentPwd(userId, password);
  //      System.out.println(boo);
    }

    /**
     * 用户修改密码
     */
    @Test
    public void updatePassword() {
        long userId = 7;
        String password = "1237536";

  //      userServiceImpl.updatePassword(userId, password);
    }

}
