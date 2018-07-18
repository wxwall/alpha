package com.asiainfo.crm.busi.dao;

import com.asiainfo.crm.busi.MktResource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MktResourceMapper {
    int deleteByPrimaryKey(Integer mktResId);

    int insert(MktResource record);

    int insertSelective(MktResource record);

    MktResource selectByPrimaryKey(Integer mktResId);

    int updateByPrimaryKeySelective(MktResource record);

    int updateByPrimaryKey(MktResource record);


    @Update("update mkt_resource set MKT_RES_NAME='批量分片修改-4' where MKT_RES_ID in (1000,1001)")
    int updateMktMktResource();
    @Update("udal dt start")
    public int udalDtStart();

    @Update("update mkt_resource set MKT_RES_NAME='批量分片修改-4-without' where MKT_RES_ID in (1000,1001)")
    public int updateMktResourcesWithOutDtStart();



}