package com.asiainfo.crm.config.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 主要是扫描technology-dbua.jar的包。这个需要组件调整报名统一
 **/
@Configuration
@ComponentScan(
        basePackages = {"com.ai.*"}
)
public class Ctg2Config {
}