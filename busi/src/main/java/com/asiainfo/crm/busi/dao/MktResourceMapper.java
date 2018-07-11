package com.asiainfo.crm.busi.dao;

import com.asiainfo.crm.busi.MktResource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MktResourceMapper {
    int deleteByPrimaryKey(Integer mktResId);

    int insert(MktResource record);

    int insertSelective(MktResource record);

    MktResource selectByPrimaryKey(Integer mktResId);

    int updateByPrimaryKeySelective(MktResource record);

    int updateByPrimaryKey(MktResource record);
}