package com.asiainfo.crm.multi.impl;

import com.asiainfo.crm.common.model.RestResult;
import com.asiainfo.crm.multi.SoWebMultiSMO;
import com.asiainfo.crm.multi.outer.SoWebServiceOuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuxiaowei on 2018/6/13
 */
@Service
public class SoWebMultiSMOImpl implements SoWebMultiSMO {

    @Autowired
    SoWebServiceOuter soWebServiceOuter;


    @Override
    public RestResult queryOrderListInfoByCustomerOrderId(String jsonParam) {
        RestResult restResult = new RestResult();
        String resout = soWebServiceOuter.queryOrderListInfoByCustomerOrderId(jsonParam);
        restResult.setMessage(resout);
        return restResult;
    }
}
