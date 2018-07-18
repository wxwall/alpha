package com.asiainfo.crm.config.common;

import com.al.crm.cache.pool.RedisManager;
import com.al.crm.cache.pool.RedisPool;
import com.al.crm.cache.routing.AbstractRedisSourceStrategy;
import com.al.crm.cache.routing.RedisSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 编程式配置
 * Created by wuxiaowei on 2018/7/18
 */
@Configuration
public class RedisConfig {

    /**
     * 设置连接实例1
     * @return
     */
    @Bean
    public RedisPool getRdisPool0() {
        RedisPool redisPool = new RedisPool();
        redisPool.setMaxTotal(200);
        redisPool.setMaxIdle(30);
        redisPool.setMaxWaitMillis(3000);
        redisPool.setMinIdle(3);
        redisPool.setServerIp("10.128.91.222:8088");
        redisPool.setPassWord("crmtest#asia@123");
        redisPool.setDatabase(4970);
        redisPool.init();
        return redisPool;
    }

    /**
     * 设置连接实例2
     * @return
     */
    @Bean
    public RedisPool getRdisPool1() {
        RedisPool redisPool = new RedisPool();
        redisPool.setMaxTotal(200);
        redisPool.setMaxIdle(30);
        redisPool.setMaxWaitMillis(3000);
        redisPool.setMinIdle(3);
        redisPool.setServerIp("10.128.91.222:8088");
        redisPool.setPassWord("crmtest#asia@123");
        redisPool.setDatabase(4970);
        redisPool.init();
        return redisPool;
    }


    @Bean
    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setRedisClient(getRedisSource());
        return redisManager;
    }

    @Bean
    public RedisSource getRedisSource() {
        Map<Object,Object> targetDataSources = new HashMap<Object,Object>();
        targetDataSources.put("rds01",getRdisPool0());
        targetDataSources.put("rds02",getRdisPool1());
        RedisSource redisSource = new RedisSource();
        redisSource.setTargetDataSources(targetDataSources);
        Class<AbstractRedisSourceStrategy> clazz = null;
        try {
            clazz = (Class<AbstractRedisSourceStrategy>) Class.forName("com.al.crm.cache.routing.RedisSourceStrategy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        redisSource.setStrategy(clazz);
        return redisSource;
    }
}
