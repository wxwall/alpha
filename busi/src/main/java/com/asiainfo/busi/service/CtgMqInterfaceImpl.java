package com.asiainfo.busi.service;

import com.ai.starter.AIMQFactory;
import com.ctg.mq.api.IMQProducer;
import com.ctg.mq.api.IMQPullConsumer;
import com.ctg.mq.api.IMQPushConsumer;
import com.ctg.mq.api.bean.ConsumeStatus;
import com.ctg.mq.api.bean.MQMessage;
import com.ctg.mq.api.bean.MQResult;
import com.ctg.mq.api.bean.MQSendResult;
import com.ctg.mq.api.exception.MQException;
import com.ctg.mq.api.exception.MQProducerException;
import com.ctg.mq.api.listener.ConsumerTopicListener;
import com.ctg.mq.api.listener.ConsumerTopicStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
                        "CTGMQ_TEST_PUSH",// topic CTGMQ_TEST
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
        IMQPullConsumer pullConsumer = aimqFactory.createPullConsumer();
        int connectResult = 0;
        try {
            connectResult = pullConsumer.connect();
        } catch (MQException e) {
            e.printStackTrace();
        }
        if (connectResult != 0) {
            return;//报出异常
        }

        try {
            List<MQResult> mqResultList = pullConsumer.consumeMessagesByTopic("CTGMQ_TEST",null,2,30000);
            for (MQResult result : mqResultList) {
                try {
                    System.out.println(result);
                    //业务处理正常，签收成功
                    pullConsumer.ackMessage(result, ConsumeStatus.CONSUME_SUCCESS);
                    //业务处理失败时，应签收失败，消息进入重试队列
                    //consumer.ackMessage(result, ConsumeStatus.RECONSUME_LATER);
                } catch (MQException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                pullConsumer.close();
            } catch (MQException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 侦听式消费
     */
    @Override
    public void pushTest() {
        IMQPushConsumer pushConsumer = aimqFactory.createPushConsumer();
        int connectResult = 0;
        try {
            connectResult = pushConsumer.connect();
        } catch (MQException e) {
            e.printStackTrace();
        }
        if (connectResult != 0) {
            return;//报出异常
        }
        try {
            pushConsumer.listenTopic("CTGMQ_TEST_PUSH", null, new ConsumerTopicListener() {
                @Override
                public ConsumerTopicStatus onMessage(List<MQResult> mqResultList) {
                    for(MQResult result : mqResultList) {
                        System.out.println(result);
                    }
                    return ConsumerTopicStatus.CONSUME_SUCCESS;//对消息批量确认(成功)
                    //return ConsumerTopicStatus.RECONSUME_LATER;//对消息批量确认(失败)
                }
            });
        } catch (MQException e) {
            e.printStackTrace();
        }

    }
}
