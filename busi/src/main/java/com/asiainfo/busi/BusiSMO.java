package com.asiainfo.busi;

import com.asiainfo.common.model.RestResult;

import java.util.List;

/**
 * Created by wuxiaowei on 2018/5/3
 */
public interface BusiSMO {

    public String queryDemo();

    public String queryJdbcDemo(String name);

    public RestResult queryDemoForFeign(String name);

    String queryConfigDemo();

    String queryConfigByApollo();

    List<BusiModel> getBusiList();

    void remove(Long id);

    void put(Long id);
}
