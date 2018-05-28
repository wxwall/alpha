package com.asiainfo.busi.service;

import com.ai.starter.AIMQFactory;
import com.ctg.mq.api.IMQProducer;
import com.ctg.mq.api.bean.MQMessage;
import com.ctg.mq.api.bean.MQSendResult;
import com.ctg.mq.api.exception.MQException;
import com.ctg.mq.api.exception.MQProducerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgMqInterfaceImpl implements CtgMqInterface {

    @Autowired
    AIMQFactory aimqFactory;

    @Override
    public void sendTest() {
        IMQProducer producer = aimqFactory.createProducer();
        int connectResult = 0;
        try {
            connectResult = producer.connect();
        } catch (MQException e) {
            e.printStackTrace();
        }
        if(connectResult != 0){
            return;
        }
        for (int i = 0; i < 3; i++) {
            try {
                MQMessage message = new MQMessage(
                        "CTGMQ_TEST",// topic
                        "ORDER_KEY_"+i,// key
                        "ORDER_TAG",//tag
                        ("HELLO ORDER BODY" + i).getBytes()// body
                );
                MQSendResult sendResult = producer.send(message);
                System.out.println(sendResult);
                //TODO
            } catch (MQProducerException e) {
                e.printStackTrace();
            }
        }
        try {
            producer.close();
        } catch (MQException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pullTest() {

    }
}
