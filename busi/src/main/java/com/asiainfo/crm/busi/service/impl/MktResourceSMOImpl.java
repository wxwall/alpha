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
    public int updateMktResource(List<String> mktResNbr) {
        int mktResource = mktResourceMapper.updateMktResource(mktResNbr);
        System.out.println(mktResource);
        return 0;
    }

    @Override
    public List<MktResource> getMktResourceList() {
        return mktResourceMapper.getMktResourceList();
    }

    @Override
    public MktResource getMktResource(Long id) {
        return mktResourceMapper.getMktResource(id);
    }
}
