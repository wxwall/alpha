package com.asiainfo.crm.busi.service;

public interface CtgMqInterface {

    void sendTest();


    void pullTest();

    /**
     * 侦听式消费
     */
    void pushTest();

}
