package com.asiainfo.crm.busi.service.impl;


import com.asiainfo.crm.busi.OrderItem;
import com.asiainfo.crm.busi.dao.OrderItemDAO;
import com.asiainfo.crm.busi.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuxiaowei on 2018/4/2
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemDAO orderItemDAO;

    @Override
    public OrderItem findByName(String name) {
        return orderItemDAO.findByName(name);
    }
}
