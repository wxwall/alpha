package com.asiainfo.crm.controller;

import com.al.crm.cache.ICache;
import com.al.crm.cache.routing.RedisContext;
import com.asiainfo.crm.ServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.CountDownLatch;

import static junit.framework.TestCase.*;

/**
 * Created by wuxiaowei on 2018/6/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class CacheControllerTest {


    private MockMvc mvc;


    @Autowired
    private ICache cache;


    /**
     * 压力测试，1000个并发同时查询
     * @throws Exception
     */
    //@Test
    public void queryCacheData() throws Exception {
        int threadCount = 1000;
        long[] currentTime = {0};
        CountDownLatch latch = new CountDownLatch(threadCount);
        for(int i = 0; i< threadCount; i++){
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //System.out.println("准备就绪：" + Thread.currentThread().getName()) ;
                        latch.await();
                        //Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.printf("开始执行：%s ,key: %s%n" ,Thread.currentThread().getName() ,String.valueOf(finalI));
                    Long begin = System.currentTimeMillis();
                    RedisContext.setRoutingId("832000");
                    cache.get(String.valueOf(finalI));
                    Long costTime = (System.currentTimeMillis() - begin);
                    System.out.printf("当前线程：%s 执行时间：%s%n ",Thread.currentThread().getName(),costTime);
                    synchronized (CacheControllerTest.class){
                        currentTime[0] += costTime;
                    }
                }
            }).start();


            latch.countDown();
        }
        while (true){
            if(latch.getCount() == 0){
                //主线程等待执行，否则子线程没跑完，主线程就结束，统计出的时间不准
                Thread.sleep(10000);
                System.out.println("耗时：" + currentTime[0]);
                break;
            }

        }


    }



    @Test
    public void insertCacheData() throws Exception {
        RedisContext.setRoutingId("832000");
        cache.put("aa","11");
        cache.put("bb","11",1,1024);
        cache.put("cc","11",1);
        cache.put("orderItem","dd","11");
        cache.put("orderItem","ee","11",1);
        cache.put("orderItem","ff","11",1,1024);
        assertFalse(cache.putIfNotExists("aa", "11"));
        final boolean gg = cache.putIfNotExists("gg", "11");
        System.out.println(gg);
        assertTrue(cache.putIfNotExists("gg","11"));
    }

    @Test
    public void deleteCacheData() throws Exception {
        RedisContext.setRoutingId("832000");
        cache.remove("aa");
        cache.put("aa","22");
        assertEquals("22",cache.get("aa"));

        cache.remove("orderItem","dd");
        cache.put("orderItem","dd","22");
        assertEquals("22",cache.get("orderItem","dd"));
    }

    @Test
    public void insertCacheDataByDir() throws Exception {
        assertNotNull(cache.get("orderItem","dd"));
    }

}