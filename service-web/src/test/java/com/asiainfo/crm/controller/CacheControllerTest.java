package com.asiainfo.crm.controller;

import com.asiainfo.crm.ServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by wuxiaowei on 2018/6/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class CacheControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    RequestBuilder request = null;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }

    @Test
    public void queryCacheData() throws Exception {
    }

    @Test
    public void insertCacheData() throws Exception {
    }

    @Test
    public void deleteCacheData() throws Exception {
    }

    @Test
    public void insertCacheDataByDir() throws Exception {
    }

}