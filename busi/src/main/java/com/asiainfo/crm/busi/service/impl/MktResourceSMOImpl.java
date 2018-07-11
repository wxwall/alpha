package com.asiainfo.crm.busi.service.impl;

import com.asiainfo.crm.busi.MktResource;
import com.asiainfo.crm.busi.dao.MktResourceMapper;
import com.asiainfo.crm.busi.service.MktResourceSMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MktResourceSMOImpl implements MktResourceSMO {

    @Autowired
    MktResourceMapper mktResourceMapper;

    @Override
    public MktResource getMktResourceById(Integer mktResId) {
        return mktResourceMapper.selectByPrimaryKey(mktResId);
    }
}
