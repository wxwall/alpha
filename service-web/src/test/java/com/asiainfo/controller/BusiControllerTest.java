package com.asiainfo.controller;

import com.asiainfo.ServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wuxiaowei on 2018/6/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class BusiControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    RequestBuilder request = null;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }

    @Test
    public void getBusi() throws Exception {

        // 1、get查一下busi列表，应该为空
        request = get("/busi/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"code\":0,\"data\":[{\"id\":2,\"name\":\"小红\"}]}")));

        // get查询值为1的busi
        request = get("/busi/1");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"code\":0,\"data\":{\"id\":1,\"name\":\"小明\"}}")));


    }

    @Test
    public void postBusi() throws Exception {
        request = post("/busi/")
                .param("id", "1")
                .param("name", "测试BusiModel");
        mvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(equalTo("{\"code\":0,\"message\":\"测试BusiModel\"}")));

    }

    @Test
    public void putBusi() throws Exception {
        request = put("/busi/1")
                .param("id", "2")
                .param("name", "测试BusiModel");
        mvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(equalTo("{\"code\":0,\"message\":\"success\"}")));
    }

    @Test
    public void deleteBusi() throws Exception {
        request = delete("/busi/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("{\"code\":0,\"message\":\"success\"}")));


    }



}