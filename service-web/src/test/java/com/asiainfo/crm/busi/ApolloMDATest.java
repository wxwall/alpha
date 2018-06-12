package com.asiainfo.crm.busi;

import com.asiainfo.crm.ServiceApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wuxiaowei on 2018/5/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class ApolloMDATest {

    @Autowired
    ApolloMDA apolloMDA;

    @Test
    public void getProperties(){
        System.out.println(apolloMDA.getJsonBeans()  + "    " + apolloMDA.getName());
        Assert.assertNotNull("小明", apolloMDA.getName());
        Assert.assertNotNull(apolloMDA.getJsonBeans());
    }
}
