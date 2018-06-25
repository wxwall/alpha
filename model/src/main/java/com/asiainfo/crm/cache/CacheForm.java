package com.asiainfo.crm.cache;

/**
 * 规范controller层入参，实体类后缀带Form
 * Created by wuxiaowei on 2018/6/22
 */
public class CacheForm {

    /** 地区ID**/
    public String areaId;

    /** key**/
    public String cache_key;

    /** value**/
    public Object cache_value;

    /** dir**/
    public String cache_dir;


    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCache_key() {
        return cache_key;
    }

    public void setCache_key(String cache_key) {
        this.cache_key = cache_key;
    }

    public Object getCache_value() {
        return cache_value;
    }

    public void setCache_value(Object cache_value) {
        this.cache_value = cache_value;
    }

    public String getCache_dir() {
        return cache_dir;
    }

    public void setCache_dir(String cache_dir) {
        this.cache_dir = cache_dir;
    }
}
