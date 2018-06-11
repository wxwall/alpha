package com.asiainfo.busi.impl;

import com.asiainfo.busi.ApolloMDA;
import com.asiainfo.busi.BusiMDA;
import com.asiainfo.busi.BusiModel;
import com.asiainfo.busi.BusiSMO;
import com.asiainfo.busi.dao.BusiDao;
import com.asiainfo.common.model.RestResult;
import com.asiainfo.multi.BusiMultiSMO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxiaowei on 2018/5/3
 */
@Service
public class BusiSMOImpl implements BusiSMO {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @SuppressWarnings("SpringJavaAutowiringInspection")
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

    @Override
    public List<BusiModel> getBusiList() {
        List<BusiModel> busiModels = new ArrayList<BusiModel>();
        BusiModel busiModel = new BusiModel();
        busiModel.setId(1);
        busiModel.setName("小明");
        BusiModel busiModel2 = new BusiModel();
        busiModel2.setId(2);
        busiModel2.setName("小红");
        busiModels.add(busiModel2);
        return busiModels;
    }

    @Override
    public void remove(Long id) {
        logger.debug("根据id={},删除成功",id);
    }

    @Override
    public void put(Long id) {
        logger.debug("根据id={},修改成功",id);
    }
}
