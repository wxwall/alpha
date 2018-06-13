package com.asiainfo.crm.multi;

import com.asiainfo.crm.common.model.RestResult;

/**
 * 访问非中心的外部系统，需要使用http远程调用
 * Created by wuxiaowei on 2018/6/13
 */
public interface SoWebMultiSMO {

    public RestResult queryOrderListInfoByCustomerOrderId(String jsonParam);
}
