package com.asiainfo.crm.busi.dao;

import com.ai.dtstart.UdalDtStartDao;
import com.asiainfo.crm.busi.MktResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
//extends UdalDtStartDao
@Mapper
public interface SequenceDao extends UdalDtStartDao{

    @Select("SELECT ${seqName}.nextval(#{seqNum})")
    List<Long> querySeqs(@Param("seqName") String seqName, @Param("seqNum") Integer seqNum);
}
