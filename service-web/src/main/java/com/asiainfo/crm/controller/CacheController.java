package com.asiainfo.crm.controller;

import com.al.crm.cache.pool.RedisManager;
import com.al.crm.cache.routing.RedisContext;
import com.alibaba.fastjson.JSONArray;
import com.asiainfo.crm.busi.BusiModel;
import com.asiainfo.crm.common.annotation.SysLog;
import com.asiainfo.crm.common.controller.AbstractController;
import com.asiainfo.crm.common.exception.BaseException;
import com.asiainfo.crm.common.model.RestResult;
import com.ctg.itrdc.cache.pool.CtgJedisPoolException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CacheController extends AbstractController {

    @Autowired
    private RedisManager cache;


    @SysLog
    @ApiOperation(value="查询Demo",notes="查询对应Key的Value值")
    @RequestMapping(value = "/queryCacheData" ,method = RequestMethod.POST)
    public RestResult queryCacheData(@RequestBody Map<String,String> map){
        RedisContext.setRoutingId(map.get("areaId"));
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("date",cache.get(map.get("cache_key")));
        RestResult<Map> restResult = new RestResult<Map>();
        restResult.setMessage("success");
        restResult.setData(resultMap);

        return restResult;
    }

    @ApiOperation(value="插入demo",notes="插入缓存数据")
    @RequestMapping(value = "/insertCacheData" ,method = RequestMethod.POST)
    public String insertCacheData(@RequestBody Map<String,String> map){
        RedisContext.setRoutingId(map.get("areaId"));
        cache.put(map.get("cache_key"),map.get("cache_value"));
        return cache.get(map.get("cache_key"))+",success insert!!";
    }

    @ApiOperation(value="删除demo",notes="插入缓存数据")
    @RequestMapping(value = "/deleteCacheData" ,method = RequestMethod.POST)
    public String deleteCacheData(@RequestBody Map<String,String> map){
        RedisContext.setRoutingId(map.get("areaId"));
        cache.remove(map.get("cache_key"));
        return "success delete!!";
    }

    @ApiOperation(value="插入带目录的缓存值",notes="插入缓存数据")
    @RequestMapping(value = "/insertCacheDataByDir" ,method = RequestMethod.POST)
    public String insertCacheDataByDir(@RequestBody Map<String,String> map){
        RedisContext.setRoutingId(map.get("areaId"));
        cache.put(map.get("cache_dir"),map.get("cache_key"),map.get("cache_value"));
        return "success insert!!";
    }
}
