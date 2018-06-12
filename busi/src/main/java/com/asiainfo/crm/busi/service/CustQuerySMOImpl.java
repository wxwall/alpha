package com.asiainfo.crm.busi.service;

import com.asiainfo.crm.busi.dao.ICustQueryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustQuerySMOImpl implements  ICustQuerySMO{

    @Autowired
    private ICustQueryDAO custQueryDAO;

    public Map queryStaffBaseInfo(Map param){
        return custQueryDAO.queryStaffBaseInfo(param);
    }

    @Override
    public Map queryProdInstByCond(Map param) {
        return custQueryDAO.queryProdInstByCond(param);
    }
}
