package com.asiainfo.crm.busi.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustOperSMOImpl implements  ICustOperSMO{

    @Override
    public void updateProdInst(List<Long> prodInstIds) {

        for(Long prodInstId : prodInstIds){
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("prodInstId",prodInstId);
            param.put("remark", "测试事务回滚能力...");
        }

        //异常抛出
        Long.parseLong("aaaa");

    }
}
