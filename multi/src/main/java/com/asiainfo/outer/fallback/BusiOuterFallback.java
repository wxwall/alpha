package com.asiainfo.outer.fallback;

import com.asiainfo.common.model.RestResult;
import org.springframework.stereotype.Component;
import com.asiainfo.outer.BusiOuter;

/**
 * Created by wuxiaowei on 2018/5/3
 */
@Component
public class BusiOuterFallback implements BusiOuter {


    @Override
    public RestResult queryJdbcDemo(String name) {
        RestResult restResult = new RestResult();
        restResult.setMessage("熔断调用OuterFallback.queryDemoById方法");
        return restResult;
    }
}