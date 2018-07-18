package com.asiainfo.crm.busi.service.impl;

import com.asiainfo.crm.busi.MktResource;
import com.asiainfo.crm.busi.dao.MktResourceMapper;
import com.asiainfo.crm.busi.service.MktResourceSMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MktResourceSMOImpl implements MktResourceSMO {

    @Autowired
    MktResourceMapper mktResourceMapper;

    @Override
    public MktResource getMktResourceById(Integer mktResId) {
        return mktResourceMapper.selectByPrimaryKey(mktResId);
    }

    @Override
    public int insertMktResources(List<MktResource> records) {
        mktResourceMapper.insert(records.get(0));
        mktResourceMapper.insert(records.get(1));
        return 0;
    }

    @Override
    public int updateMktResources() {
        mktResourceMapper.udalDtStart();
        return mktResourceMapper.updateMktMktResource();
    }

    public int updateMktResourcesWithOutDtStart() {
        return mktResourceMapper.updateMktResourcesWithOutDtStart();
    }
}
