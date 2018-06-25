package com.asiainfo.crm.controller;

import com.al.crm.cache.ICache;
import com.al.crm.cache.routing.RedisContext;
import com.asiainfo.crm.cache.CacheForm;
import com.asiainfo.crm.common.controller.AbstractController;
import com.asiainfo.crm.common.model.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/cache")
public class CacheController extends AbstractController {

    @Autowired
    private ICache cache;

    @ApiOperation(value="查询Demo",notes="查询对应Key的Value值")
    @RequestMapping(value = "/queryCacheData" ,method = RequestMethod.POST)
    public RestResult queryCacheData(@ModelAttribute CacheForm cacheForm){
        RedisContext.setRoutingId(cacheForm.getAreaId());
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("date",cache.get(cacheForm.getCache_key()));
        RestResult<Map> restResult = new RestResult<Map>();
        restResult.setMessage("success");
        restResult.setData(resultMap);
        return restResult;
    }

    @ApiOperation(value="插入demo",notes="插入缓存数据")
    @RequestMapping(value = "/insertCacheData" ,method = RequestMethod.POST)
    public RestResult insertCacheData(@ModelAttribute CacheForm cacheForm){


        cache.put(cacheForm.getCache_key(),cacheForm.getCache_value());
        RestResult restResult = new RestResult();
        restResult.setMessage(cache.get(cacheForm.getCache_key())+",success insert!!");
        return restResult;
    }

    @ApiOperation(value="删除demo",notes="删除缓存数据")
    @RequestMapping(value = "/deleteCacheData" ,method = RequestMethod.POST)
    public RestResult<String> deleteCacheData(@ModelAttribute CacheForm cacheForm){
        cache.remove(cacheForm.getCache_key());
        RestResult<String> restResult = new RestResult<String>();
        restResult.setMessage("success delete!!");
        return restResult;
    }

    @ApiOperation(value="插入带目录的缓存值",notes="插入缓存数据")
    @RequestMapping(value = "/insertCacheDataByDir" ,method = RequestMethod.POST)
    public RestResult<String> insertCacheDataByDir(@ModelAttribute CacheForm cacheForm){
        RedisContext.setRoutingId(cacheForm.getAreaId());
        cache.put(cacheForm.getCache_key(),cacheForm.getCache_key(),cacheForm.getCache_value());
        RestResult<String> restResult = new RestResult<String>();
        restResult.setMessage("success delete!!");
        return restResult;
    }
}
