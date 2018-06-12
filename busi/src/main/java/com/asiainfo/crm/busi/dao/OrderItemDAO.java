package com.asiainfo.crm.busi.dao;

import com.asiainfo.crm.busi.OrderItem;
import com.asiainfo.crm.common.dao.BaseDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderItemDAO extends BaseDao<OrderItem> {

    @Select("SELECT * FROM ORDER_ITEM WHERE NAME = #{name}")
    OrderItem findByName(@Param("name") String name);

    @Insert("INSERT INTO ORDER_ITEM(NAME) VALUES(#{name})")
    int insert(@Param("name") String name);

}