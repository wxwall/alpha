package com.asiainfo.busi.impl;

import com.asiainfo.busi.BusiMDA;
import com.asiainfo.busi.BusiSMO;
import com.asiainfo.busi.dao.BusiDao;
import com.asiainfo.busi.BusiModel;
import com.asiainfo.common.model.RestResult;
import com.asiainfo.busi.ApolloMDA;
import com.asiainfo.multi.BusiMultiSMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuxiaowei on 2018/5/3
 */
@Service
public class BusiSMOImpl implements BusiSMO {

    @Autowired
    BusiDao busiDao;

    @Autowired
    BusiMultiSMO busiMultiSMO;

    @Autowired
    BusiMDA prodOfferMDA;

    @Autowired
    ApolloMDA apolloMDA;

    @Override
    public String queryDemo() {
        return "hello queryDemo";
    }

    @Override
    public String queryJdbcDemo(String name) {
        BusiModel busiModel = busiDao.findByName(name);
        return busiModel.getName();
    }

    @Override
    public RestResult queryDemoForFeign(String name) {
        return busiMultiSMO.queryJdbcDemo(name);
    }

    @Override
    public String queryConfigDemo() {
        return prodOfferMDA.getProdOfferName() +"   " + prodOfferMDA.getProdOfferId();
    }

    @Override
    public String queryConfigByApollo() {
        return apolloMDA.getName() + "   " + apolloMDA.getAge() + "   " + apolloMDA.getJsonBeans();
    }
}
