package com.asiainfo.busi.dao;

import com.asiainfo.busi.BusiModel;
import com.asiainfo.common.dao.BaseDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by wuxiaowei on 2018/5/4
 */

@Mapper
public interface BusiDao extends BaseDao<BusiModel> {

    @Select("SELECT * FROM BusiModel WHERE NAME = #{name}")
    BusiModel findByName(@Param("name") String name);

    @Insert("INSERT INTO BusiModel(NAME) VALUES(#{name})")
    int insert(@Param("name") String name);

}