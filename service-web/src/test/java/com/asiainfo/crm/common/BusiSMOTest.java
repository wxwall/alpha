package com.asiainfo.crm.common;

import com.asiainfo.crm.ServiceApplication;
import com.asiainfo.crm.busi.service.BusiSMO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wuxiaowei on 2018/7/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class BusiSMOTest {

    @Autowired
    BusiSMO busiSMO;

    @Test
    public void queryDemo(){
        String s = busiSMO.queryDemo();
        System.out.println(s);
    }



}
