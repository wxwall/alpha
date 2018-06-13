package com.asiainfo.crm.multi.outer.fallback;

import com.asiainfo.crm.multi.outer.SoWebServiceOuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by wuxiaowei on 2018/6/13
 */
@Component
public class SoWebServiceOuterFallback implements SoWebServiceOuter {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public String queryOrderListInfoByCustomerOrderId(String jsonParam) {
        //todo 日志打印记录多字段
        logger.info("调用失败，入参：" + jsonParam);
        return "调用失败,入参：" + jsonParam;
    }
}
