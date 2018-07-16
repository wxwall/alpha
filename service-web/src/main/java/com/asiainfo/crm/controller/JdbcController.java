package com.asiainfo.crm.controller;

import com.ai.datasources.DataSourceContextHolder;
import com.asiainfo.crm.busi.MktResource;
import com.asiainfo.crm.busi.service.MktResourceSMO;
import com.asiainfo.crm.common.model.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Restful
 * 对分布式数据库的基本操作(mktResource表)
 * 注意：数据源选择到时候根据业务需求统一放到aop中处理
 * Created by wuxiaowei on 2018/7/16
 */
@RestController
@RequestMapping(value = "/jdbc")
public class JdbcController {

    @Autowired
    MktResourceSMO mktResourceSMO;

    @ApiOperation(value="处理\"/mktResource/\"的PUT请求，用来更新mktResource信息", notes="数据库中涉及多个分片更新，注意事务")
    @RequestMapping(value="/", method= RequestMethod.PUT)
    public RestResult putMktResource() {
        DataSourceContextHolder.setDataSourceType("ds2");
        RestResult<MktResource> restResult = new RestResult<MktResource>();
        MktResource mktResource = new MktResource();
        List<String> mktResNbr = new ArrayList<String>();
        mktResNbr.add("1");
        mktResNbr.add("2");
        int i = mktResourceSMO.updateMktResource(mktResNbr);
        restResult.setData(mktResource);
        return restResult;
    }


    @ApiOperation(value="处理\"/jdbc/\"的GET请求", notes="查询所有的返回值")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public RestResult getMktResource() {
        DataSourceContextHolder.setDataSourceType("ds2");
        RestResult<List<MktResource>> restResult = new RestResult<List<MktResource>>();
        List<MktResource> mktResources = mktResourceSMO.getMktResourceList();
        restResult.setData(mktResources);
        return restResult;
    }

    @ApiOperation(value="处理\"/jdbc/{id}\"的GET请求", notes="查询id对应的值")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public RestResult getMktResourceById(@PathVariable Long id) {
        DataSourceContextHolder.setDataSourceType("ds2");
        RestResult<MktResource> restResult = new RestResult<MktResource>();
        MktResource mktResources = mktResourceSMO.getMktResource(id);
        restResult.setData(mktResources);
        return restResult;
    }

}
