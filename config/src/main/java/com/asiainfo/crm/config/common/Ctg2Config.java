package com.asiainfo.crm.config.common;

import com.ai.hint.HintInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 主要是扫描technology-dbua.jar的包。这个需要组件调整报名统一
 **/
@Configuration
@ImportResource("classpath:/spring-boot/spring-context.xml")
public class Ctg2Config {
    @Bean
    public Interceptor hintInterceptor(){
        return new HintInterceptor();
    }
}