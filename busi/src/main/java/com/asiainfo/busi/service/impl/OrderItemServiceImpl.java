package com.asiainfo.busi.service.impl;


import com.asiainfo.busi.dao.OrderItemDAO;
import com.asiainfo.busi.model.OrderItem;
import com.asiainfo.busi.service.OrderItemService;
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
