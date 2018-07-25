package com.asiainfo.crm.busi.service.impl;

import com.ai.datasources.DataSourceContextHolder;
import com.asiainfo.crm.busi.MktResource;
import com.asiainfo.crm.busi.dao.MktResourceMapper;
import com.asiainfo.crm.busi.service.MktResourceSMO;
import com.asiainfo.crm.busi.service.SequenceSMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MktResourceSMOImpl implements MktResourceSMO {

    @Autowired
    MktResourceMapper mktResourceMapper;
    @Autowired
    SequenceSMO sequenceSMO;

    @Override
    public MktResource getMktResourceById(Integer mktResId) {
        return mktResourceMapper.selectByPrimaryKey(mktResId);
    }

    @Override
    public int updateMktResource(List<String> mktResNbr) {
        mktResourceMapper.udalDtStart();
        return mktResourceMapper.updateMktResource(mktResNbr);
    }

    @Override
    public List<MktResource> getMktResourceList() {
        return mktResourceMapper.getMktResourceList();
    }

    @Override
    public MktResource getMktResource(Long id) {
        return mktResourceMapper.getMktResource(id);
    }

    @Override
    public int insertMktResources(List<MktResource> records) {
        mktResourceMapper.udalDtStart();
        mktResourceMapper.insert(records.get(0));
        mktResourceMapper.insert(records.get(1));
        //int i=1/0;//制造异常事务回滚
        List<Long> longs = querySeqs();
        //
        mktResourceMapper.updateMktMktResourceById(records.get(0).getMktResId());
        mktResourceMapper.updateMktMktResourceById(records.get(1).getMktResId());
        return 0;
    }

    @Override
    public int insertMktResourcesWithOutDt(List<MktResource> records) {
        mktResourceMapper.insert(records.get(0));
        mktResourceMapper.insert(records.get(1));
        mktResourceMapper.updateMktMktResourceById(records.get(0).getMktResId());
        mktResourceMapper.updateMktMktResourceById(records.get(1).getMktResId());
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

    /**
     * 事务内 切换数据源另起事务样例
     * @return
     */
    public List<Long> querySeqs(){
        //测试结论 在同一个事务内。无法通过切换线程变量切换数据源了。合理
        //只能 配置一个专门的另外启事务的方法。提前切换数据源线程变量。可以实现
        String dataSourceType = DataSourceContextHolder.getDataSourceType();
        DataSourceContextHolder.setDataSourceType("ds1");
        //数据操作在ds2,数据查询切换到ds1，再另起事务
        String  seqName = "PERFTEST_SEQ_CRM_CUST_ID";
        //String  seqName = "SEQ_TEST";
        List<Long> seqTest = sequenceSMO.sequenceByNameAndNum(seqName, 5);
        DataSourceContextHolder.setDataSourceType(dataSourceType);
        return seqTest;
    }

    public int insertMktResourcesStart(List<MktResource> records){
        return insertMktResourcesWithOutDt(records);
    }
}
