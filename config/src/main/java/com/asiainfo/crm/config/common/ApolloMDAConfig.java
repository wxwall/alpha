package com.asiainfo.crm.config.common;

import com.asiainfo.crm.busi.ApolloMDA;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wuxiaowei on 2018/5/18
 */
@Configuration
@EnableApolloConfig
public class ApolloMDAConfig {

    @Bean
    public ApolloMDA javaConfigBean() {

        return new ApolloMDA();

    }
}
