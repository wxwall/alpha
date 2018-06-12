package com.asiainfo.crm.multi.impl;

import com.asiainfo.crm.common.model.RestResult;
import com.asiainfo.crm.multi.BusiMultiSMO;
import com.asiainfo.crm.multi.outer.BusiOuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by wuxiaowei on 2018/5/4
 */
@Service
public class BusiMultiSMOImpl implements BusiMultiSMO {

    @Autowired
    BusiOuter busiOuter;

    @Override
    public RestResult queryJdbcDemo(String name){
        return busiOuter.queryJdbcDemo(name);
    }
}
