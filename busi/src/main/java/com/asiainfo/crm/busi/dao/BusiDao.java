package com.asiainfo.crm.busi.dao;

import com.asiainfo.crm.busi.BusiModel;
import com.asiainfo.crm.common.dao.BaseDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * Created by wuxiaowei on 2018/5/4
 */

@Mapper
public interface BusiDao extends BaseDao<BusiModel> {

    @Select("SELECT * FROM BusiModel WHERE NAME = #{name}")
    BusiModel findByName(@Param("name") String name);

    @Insert("INSERT INTO BusiModel(NAME) VALUES(#{name})")
    int insert(@Param("name") String name);

}