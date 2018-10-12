package com.asiainfo.crm.busi.service.impl;

import com.asiainfo.crm.busi.dao.SequenceDao;
import com.asiainfo.crm.busi.service.SequenceSMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SequenceSMOImpl implements SequenceSMO{

    @Autowired
    SequenceDao sequenceDao;

/*    public List<Long> querySeqs(String seqName, Integer seqNum) {
        String dataSourceType = DataSourceContextHolder.getDataSourceType();
        DataSourceContextHolder.setDataSourceType("ds1");
        List<Long> longs = sequenceByNameAndNum(seqName, seqNum);
        DataSourceContextHolder.setDataSourceType(dataSourceType);
        return longs;
    }*/

    public List<Long> sequenceByNameAndNum(String seqName, Integer seqNum) {
        return sequenceDao.querySeqs(seqName, seqNum);
    }
}
