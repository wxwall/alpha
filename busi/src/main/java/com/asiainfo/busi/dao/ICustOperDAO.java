package com.asiainfo.busi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ICustOperDAO {

    public void updateProdInst(Map<String, Object> param);
}
