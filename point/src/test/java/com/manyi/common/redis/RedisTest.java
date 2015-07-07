package com.manyi.common.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
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
*/
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations={"classpath:spring-point-servlet.xml"})
public class RedisTest {
    @Test
    public void testRedisSimpleSingle()
    {
               System.out.println("hello world");
    }

//
//    @Autowired
//    private JedisCluster client;
//    @Test
//    public void testRedisSimpleSingle()
//    {
////        ApplicationContext aCtx = new FileSystemXmlApplicationContext(
////                "classpath:spring-servlet.xml");
////        RedisClientTemplate redisClient = (RedisClientTemplate)aCtx.getBean("redisClientTemplate");
////        redisClient.set("test", "abc");
////        System.out.println(redisClient.get("test"));
//        Jedis client = new Jedis("192.168.1.102", 7000);
//        Date dt= new Date();
//        Long time= dt.getTime();
//        client.set("cluster", "lslslsls");
//        Date dt2= new Date();
//        Long time2= dt2.getTime();
//        System.out.println("存储第一个用时 ： " +(time2 - time)  );
//        System.out.println( client.get("cluster") );
//        Date dt3= new Date();
//        Long time3= dt3.getTime();
//        System.out.println("获取第一个用时 ： " +(time3 - time2)  );
//        client.del("cluster");
//        Date dt4= new Date();
//        Long time4= dt4.getTime();
//        System.out.println("删除第一个用时 ： " +(time4 - time3)  );
//        System.out.println(client.get("cluster"));
//        Date dt5= new Date();
//        Long time5= dt5.getTime();
//        System.out.println("获取第二个用时 ： " +(time5 - time4)  );
//        client.set("cluster1", "lslslsls1");
//        Date dt6= new Date();
//        Long time6= dt6.getTime();
//
//        System.out.println("存储第二个用时 ： " +(time6 - time5)  );
//        System.out.println( client.get("cluster1") );
//        Date dt7= new Date();
//        Long time7= dt7.getTime();
//        System.out.println("获取第三个用时 ： " +(time7 - time6)  );
//        client.set("cluster2", "lslslsls2");
//        Date dt8= new Date();
//        Long time8= dt8.getTime();
//        System.out.println("存储第三个用时 ： " +(time8 - time7)  );
//        System.out.println( client.get("cluster2") );
//        Date dt9= new Date();
//        Long time9= dt9.getTime();
//        System.out.println("获取第四个用时 ： " +(time9 - time8)  );
//    }
//
//    @Test
//    public void testSimplePool()
//    {
//
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxIdle(10);
//        config.setMaxTotal(30);
//        config.setMaxWaitMillis(3*1000);
//
//// 在应用初始化的时候生成连接池
//        JedisPool pool = new JedisPool(config, "192.168.1.102", 7000);
//
//// 在业务操作时，从连接池获取连接
//        Jedis client = pool.getResource();
//        try {
//            // 执行指令
//            Date dt= new Date();
//            Long time= dt.getTime();
//            client.set("simple", "lslslsls");
//            Date dt2= new Date();
//            Long time2= dt2.getTime();
//            System.out.println("存储第一个用时 ： " +(time2 - time)  );
//            System.out.println( client.get("simple") );
//            Date dt3= new Date();
//            Long time3= dt3.getTime();
//            System.out.println("获取第一个用时 ： " +(time3 - time2)  );
//            client.del("simple");
//            Date dt4= new Date();
//            Long time4= dt4.getTime();
//            System.out.println("删除第一个用时 ： " +(time4 - time3)  );
//            System.out.println(client.get("simple"));
//            Date dt5= new Date();
//            Long time5= dt5.getTime();
//            System.out.println("获取第二个用时 ： " +(time5 - time4)  );
//            client.set("simple1", "lslslsls1");
//            Date dt6= new Date();
//            Long time6= dt6.getTime();
//
//            System.out.println("存储第二个用时 ： " +(time6 - time5)  );
//            System.out.println( client.get("simple1") );
//            Date dt7= new Date();
//            Long time7= dt7.getTime();
//            System.out.println("获取第三个用时 ： " +(time7 - time6)  );
//            client.set("simple2", "lslslsls2");
//            Date dt8= new Date();
//            Long time8= dt8.getTime();
//            System.out.println("存储第三个用时 ： " +(time8 - time7)  );
//            System.out.println( client.get("simple2") );
//            Date dt9= new Date();
//            Long time9= dt9.getTime();
//            System.out.println("获取第四个用时 ： " +(time9 - time8)  );
//        } catch (Exception e) {
//            // TODO: handle exception
//        } finally {
//            // 业务操作完成，将连接返回给连接池
//            if (null != client) {
//                pool.returnResource(client);
//            }
//        } // end of try block
//
//// 应用关闭时，释放连接池资源
//        pool.destroy();
//    }
//
//
//
//    @Test
//    public void testRedisCluster()
//    {
//        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//        //Jedis Cluster will attempt to discover cluster nodes automatically
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7000));
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7001));
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7002));
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7003));
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7004));
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7005));
//       // JedisCluster jc = new JedisCluster(jedisClusterNodes);
//
//        GenericObjectPoolConfig con =   new  GenericObjectPoolConfig();
//        con.setMaxIdle(20);
//        con.setMaxTotal(200);
//        con.setMaxWaitMillis(2000);
//        con.setTestOnBorrow(true);
//        JedisCluster jc = new JedisCluster(jedisClusterNodes,2000,1000,con);
//        jc.set("test22", "hahahahahah ");
//        String value = jc.get("test22") ;
//
//    }
//
//    @Test
//    public void testRedisClusterPoolAndSpring()
//    {
//
//        Date dt= new Date();
//        Long time= dt.getTime();
//        client.set("cluster", "lslslsls");
//        Date dt2= new Date();
//        Long time2= dt2.getTime();
//        System.out.println("存储第一个用时 ： " +(time2 - time)  );
//        System.out.println( client.get("cluster") );
//        Date dt3= new Date();
//        Long time3= dt3.getTime();
//        System.out.println("获取第一个用时 ： " +(time3 - time2)  );
//        client.del("cluster");
//        Date dt4= new Date();
//        Long time4= dt4.getTime();
//        System.out.println("删除第一个用时 ： " +(time4 - time3)  );
//        System.out.println(client.get("cluster"));
//        Date dt5= new Date();
//        Long time5= dt5.getTime();
//        System.out.println("获取第二个用时 ： " +(time5 - time4)  );
//        client.set("cluster1", "lslslsls1");
//        Date dt6= new Date();
//        Long time6= dt6.getTime();
//
//        System.out.println("存储第二个用时 ： " +(time6 - time5)  );
//        System.out.println( client.get("cluster1") );
//        Date dt7= new Date();
//        Long time7= dt7.getTime();
//        System.out.println("获取第三个用时 ： " +(time7 - time6)  );
//        client.set("cluster2", "lslslsls2");
//        Date dt8= new Date();
//        Long time8= dt8.getTime();
//        System.out.println("存储第三个用时 ： " +(time8 - time7)  );
//        System.out.println( client.get("cluster2") );
//        Date dt9= new Date();
//        Long time9= dt9.getTime();
//        System.out.println("获取第四个用时 ： " +(time9 - time8)  );
//
//    }
//
//    @Test
//    public void testRedisClusterXingneng()
//    {
//        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//        //Jedis Cluster will attempt to discover cluster nodes automatically
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7000));
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7001));
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7002));
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7003));
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7004));
//        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 7005));
//        // JedisCluster jc = new JedisCluster(jedisClusterNodes);
//
//        GenericObjectPoolConfig con =   new  GenericObjectPoolConfig();
//        con.setMaxIdle(20);
//        con.setMaxTotal(200);
//        con.setMaxWaitMillis(2000);
//        con.setTestOnBorrow(true);
//        JedisCluster jc = new JedisCluster(jedisClusterNodes,2000,12);
//        Date dt= new Date();
//        Long time= dt.getTime();
//        jc.set("cluster", "lslslsls");
//        Date dt2= new Date();
//        Long time2= dt2.getTime();
//        System.out.println("存储第一个用时 ： " +(time2 - time)  );
//        System.out.println( client.get("cluster") );
//        Date dt3= new Date();
//        Long time3= dt3.getTime();
//        System.out.println("获取第一个用时 ： " +(time3 - time2)  );
//        jc.del("cluster");
//        Date dt4= new Date();
//        Long time4= dt4.getTime();
//        System.out.println("删除第一个用时 ： " +(time4 - time3)  );
//        System.out.println(client.get("cluster"));
//        Date dt5= new Date();
//        Long time5= dt5.getTime();
//        System.out.println("获取第二个用时 ： " +(time5 - time4)  );
//        jc.set("cluster1", "lslslsls1");
//        Date dt6= new Date();
//        Long time6= dt6.getTime();
//
//        System.out.println("存储第二个用时 ： " +(time6 - time5)  );
//        System.out.println( client.get("cluster1") );
//        Date dt7= new Date();
//        Long time7= dt7.getTime();
//        System.out.println("获取第三个用时 ： " +(time7 - time6)  );
//        jc.set("cluster2", "lslslsls2");
//        Date dt8= new Date();
//        Long time8= dt8.getTime();
//        System.out.println("存储第三个用时 ： " +(time8 - time7)  );
//        System.out.println( client.get("cluster2") );
//        Date dt9= new Date();
//        Long time9= dt9.getTime();
//        System.out.println("获取第四个用时 ： " +(time9 - time8)  );
//    }



}
