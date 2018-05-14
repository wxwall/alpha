package com.asiainfo.busi.service;


import com.asiainfo.busi.model.OrderItem;

/**
 * 订单服务
 * Created by wuxiaowei on 2018/4/2
 */
public interface OrderItemService  {


    OrderItem findByName(String name);

}
