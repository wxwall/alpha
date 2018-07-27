package com.asiainfo.crm.controller;

import com.asiainfo.crm.AbstractTest;
import com.asiainfo.crm.seq.SeqUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wuxiaowei on 2018/7/6
 */
public class MonitorControllerTest extends AbstractTest {


    RequestBuilder request = null;


    @Autowired
    SeqUtil seqUtil;


    @Test
    public void instances() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/monitor/instances")
                .param("service_id","SERVICE-WEB"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    /**
     * 并发多线程测试，检查是否重复ID
     */
    @Test
    public void concurrentQuerySeq18(){
        int countDownNum = 1000;
        List list = new ArrayList<String>(1000);
        CountDownLatch countDownLatch = new CountDownLatch(countDownNum);
        for (int i = 0; i < countDownNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String s = seqUtil.querySeq18();
                    list.add(s);
                }
            }).start();
            countDownLatch.countDown();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("去重前" + list.size());
        //通过java8新特性去重
        List newList = (List) list.stream().distinct().collect(Collectors.toList());

        System.out.println("去重后" + newList.size());


    }


    @Test
    public void querySeq18() throws Exception {
        for (int i = 0; i < 3; i++) {
            mvc.perform(MockMvcRequestBuilders
                    .get("/monitor/querySeq18"))
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }

    @Test
    public void querySeq16() throws Exception {
        for (int i = 0; i < 3; i++){
            mvc.perform(MockMvcRequestBuilders
                    .get("/monitor/querySeq16"))
                    .andExpect(status().isOk())
                    .andDo(print());
        }

    }



}