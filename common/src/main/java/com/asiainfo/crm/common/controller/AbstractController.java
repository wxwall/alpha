package com.asiainfo.crm.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller公共组件
 */
public abstract class AbstractController {

    /**
     * transient: 防止Logger 对象被序列化，占用序列化开销
     */
    protected transient  Logger logger = LoggerFactory.getLogger(getClass());


}
