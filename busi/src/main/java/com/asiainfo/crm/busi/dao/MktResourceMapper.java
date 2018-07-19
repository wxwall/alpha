package com.asiainfo.crm.busi.dao;

import com.asiainfo.crm.busi.MktResource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
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


    /**
     * 用注解方式
     */
    @Update({"<script>" +
            "update mkt_resource i set i.MKT_RES_NAME = '小白' WHERE i.mkt_res_id in " +
            "<foreach item='item' index='index' collection='mktResNbr' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>"
    })
    int updateMktResource(@Param("mktResNbr") List<String> mktResNbr);


    @Select("SELECT * FROM mkt_resource i")
    List<MktResource> getMktResourceList();

    @Select("SELECT * FROM mkt_resource i where mkt_res_id = #{id}")
    MktResource getMktResource(@Param("id") Long id);


    @Update("update mkt_resource set MKT_RES_NAME='批量分片修改-4' where MKT_RES_ID in (1003,1004)")
    int updateMktMktResource();
    @Update("udal dt start")
    public int udalDtStart();

    @Update("update mkt_resource set MKT_RES_NAME='批量分片修改-4-without' where MKT_RES_ID in (1000,1001)")
    public int updateMktResourcesWithOutDtStart();

    @Update("update mkt_resource set MKT_RES_NAME='批量分片修改-4' where MKT_RES_ID = #{id}")
    int updateMktMktResourceById(@Param("id") Integer id);



}