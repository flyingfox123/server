package redis;

import com.manyi.common.redis.RedisClientTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/4/17.

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-common-servlet.xml"})*/
public class RedisTest {


   /* @Autowired
    RedisClientTemplate client;
    @Test
    public void testRedisSimpleSingle()
    {
        Date dt= new Date();
        Long time= dt.getTime();
        client.set("cluster", "lslslsls");
        Date dt2= new Date();
        Long time2= dt2.getTime();
        System.out.println("存储第一个用时 ： " +(time2 - time)  );
        System.out.println( client.get("cluster") );
        Date dt3= new Date();
        Long time3= dt3.getTime();
        System.out.println("获取第一个用时 ： " +(time3 - time2)  );
        client.del("cluster");
        Date dt4= new Date();
        Long time4= dt4.getTime();
        System.out.println("删除第一个用时 ： " +(time4 - time3)  );
        System.out.println(client.get("cluster"));
        Date dt5= new Date();
        Long time5= dt5.getTime();
        System.out.println("获取第二个用时 ： " +(time5 - time4)  );
        client.set("cluster1", "lslslsls1");
        Date dt6= new Date();
        Long time6= dt6.getTime();

        System.out.println("存储第二个用时 ： " +(time6 - time5)  );
        System.out.println( client.get("cluster1") );
        Date dt7= new Date();
        Long time7= dt7.getTime();
        System.out.println("获取第三个用时 ： " +(time7 - time6)  );
        client.set("cluster2", "lslslsls2");
        Date dt8= new Date();
        Long time8= dt8.getTime();
        System.out.println("存储第三个用时 ： " +(time8 - time7)  );
        System.out.println( client.get("cluster2") );
        Date dt9= new Date();
        Long time9= dt9.getTime();
        System.out.println("获取第四个用时 ： " +(time9 - time8)  );
    }*/

}
