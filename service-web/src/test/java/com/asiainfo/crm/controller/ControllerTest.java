package com.asiainfo.crm.controller;

import com.asiainfo.crm.ServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wuxiaowei on 2018/5/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class ControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }


    @Test
    public void queryDemo() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/queryDemo"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void queryConfigDemo() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/queryConfigDemo"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void queryJdbcDemo() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/queryJdbcDemo")
                .param("name","111"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void queryDemoForFeign() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/queryDemoForFeign")
                .param("name","111"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void queryExceptionDemo() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/queryExceptionDemo"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void queryLogDemo() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/queryLogDemo"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}