package com.asiainfo.crm.controller;

import com.asiainfo.crm.AbstractTest;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 连接UDAL数据库测试demo
 * Created by wuxiaowei on 2018/7/16
 */
public class JdbcControllerTest extends AbstractTest {






    /**  批量更新**/
    @Test
    public void putMktResource() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put("/jdbc/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getMktResource() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/jdbc/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getMktResourceById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/jdbc/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }




}