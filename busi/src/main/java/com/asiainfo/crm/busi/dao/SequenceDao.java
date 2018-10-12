package com.asiainfo.crm.busi.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
//extends UdalDtStartDao
@Mapper
public interface SequenceDao {

    @Select("SELECT ${seqName}.nextval(#{seqNum})")
    List<Long> querySeqs(@Param("seqName") String seqName, @Param("seqNum") Integer seqNum);
}
