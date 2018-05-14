package com.asiainfo.busi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ICustQueryDAO {

    public Map queryStaffBaseInfo(Map param);

    public Map queryProdInstByCond(Map param);
}
