package com.asiainfo.crm.multi.outer;

import com.asiainfo.crm.common.model.RestResult;
import com.asiainfo.crm.multi.outer.fallback.BusiOuterFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 示例：调用service-beta远程服务
 * Created by wuxiaowei on 2018/5/3
 */
@FeignClient(value = "service-beta", fallback = BusiOuterFallback.class)
public interface BusiOuter {

    @RequestMapping(value = "/queryJdbcDemo",method = RequestMethod.GET)
    public RestResult queryJdbcDemo(@RequestParam(value = "name") String name);
}
