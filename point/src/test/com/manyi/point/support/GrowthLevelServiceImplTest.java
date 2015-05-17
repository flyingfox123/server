package com.manyi.point.support;

import com.manyi.point.bean.GrowthLevel;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-point-servlet.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class GrowthLevelServiceImplTest extends TestCase {

    @Autowired
    private GrowthLevelServiceImpl growthLevelService;

    public static String SERVICEID = "MY0000";
    @Before
    public void setUp() throws Exception {

//        ApplicationContext aCtx = new FileSystemXmlApplicationContext(
//                "classpath:spring-point-servlet.xml");
//        this.growthLevelService = (GrowthLevelServiceImpl) aCtx
//                .getBean("growthLevelService");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddLevel() throws Exception {

        GrowthLevel level = new GrowthLevel();
        level.setLevel(1);
        level.setServiceId(SERVICEID);
        level.setStartValue(0);
        level.setEndValue(1000);
        growthLevelService.addLevel(level);
        growthLevelService.deleteLevel(level.getId());
    }

    @Test
    public void testDeleteLevel() throws Exception {

        GrowthLevel level = new GrowthLevel();
        level.setLevel(2);
        level.setServiceId(SERVICEID);
        level.setStartValue(1001);
        level.setEndValue(2000);
        growthLevelService.addLevel(level);
        growthLevelService.deleteLevel(level.getId());
    }

    @Test
    public void testUpdateLevel() throws Exception {

        GrowthLevel level = new GrowthLevel();
        level.setLevel(3);
        level.setServiceId(SERVICEID);
        level.setStartValue(2001);
        level.setEndValue(3000);
        growthLevelService.addLevel(level);
        level.setEndValue(4000);
        growthLevelService.updateLevel(level);
        growthLevelService.deleteLevel(level.getId());

    }

    @Test
    public void testQueryLevels() throws Exception {

        GrowthLevel level ;
        for(int i=0;i<3;i++)
        {
            level  = new GrowthLevel();
            level.setLevel(i);
            level.setServiceId(SERVICEID);
            level.setStartValue(i*1000);
            level.setEndValue((i+1)*1000-1);
            growthLevelService.addLevel(level);
        }
        List<GrowthLevel> list  = growthLevelService.queryLevels(SERVICEID);
        assertNotNull(list);
        int size = list.size();
        assertEquals(size,3);
        for (GrowthLevel level1 : list)
        {
            growthLevelService.deleteLevel(level1.getId());
        }

    }

    @Test
    public void testGetLevelByGrowth() throws Exception {
        GrowthLevel level ;
        for(int i=0;i<3;i++)
        {
            level  = new GrowthLevel();
            level.setLevel(i);
            level.setServiceId(SERVICEID);
            level.setStartValue(i*1000);
            level.setEndValue((i+1)*1000-1);
            growthLevelService.addLevel(level);
        }

    }
}