package com.asiainfo.crm.busi.service;


import com.asiainfo.crm.busi.MktResource;

import java.util.List;

public interface MktResourceSMO {



    public MktResource getMktResourceById(Integer mktResId);

    int updateMktResource(List<String> mktResNbr);

    List<MktResource> getMktResourceList();

    MktResource getMktResource(Long id);
}
