package com.asiainfo.crm.busi.service;


import com.asiainfo.crm.busi.MktResource;

import java.util.List;

public interface MktResourceSMO {
    public MktResource getMktResourceById(Integer mktResId);

    public int insertMktResources(List<MktResource> records);

    public int updateMktResources();

    public int updateMktResourcesWithOutDtStart();
}
