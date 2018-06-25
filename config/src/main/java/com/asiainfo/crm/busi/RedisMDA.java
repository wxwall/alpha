package com.asiainfo.crm.busi;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisMDA {


    @Value(value = "${RedisMDA.pathPattern:/cache/**}")
    private String pathPattern;


    public String getPathPattern() {
        return pathPattern;
    }
}
