package com.asiainfo.crm.multi;

import com.asiainfo.crm.common.model.RestResult;

/**
 * 本中心访问外部中心交互，涉及多个中心交互，需要统一处理
 * Created by wuxiaowei on 2018/5/4
 */
public interface BusiMultiSMO {

    public RestResult queryJdbcDemo(String name);
}
