package com.asiainfo.crm.multi.outer;

import com.asiainfo.crm.multi.outer.fallback.SoWebServiceOuterFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Http远程调用测试环境后台接口
 * Created by wuxiaowei on 2018/6/13
 */
@FeignClient( name = "soWeb", url = "http://10.128.90.194:8888/SoWeb", fallback = SoWebServiceOuterFallback.class)
public interface SoWebServiceOuter {

    @RequestMapping(value = "/service/intf.pullingDataService/queryOrderListInfoByCustomerOrderId" , method = RequestMethod.POST)
    public String queryOrderListInfoByCustomerOrderId(@RequestBody String jsonParam);
}
