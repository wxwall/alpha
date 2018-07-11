package com.asiainfo.crm.controller;

import com.asiainfo.crm.AbstractTest;
import org.junit.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wuxiaowei on 2018/7/6
 */
public class MonitorControllerTest extends AbstractTest {


    RequestBuilder request = null;


    @Test
    public void instances() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/monitor/instances")
                .param("service_id","SERVICE-WEB"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}