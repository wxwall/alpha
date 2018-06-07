package com.asiainfo.busi.service;

import com.ai.starter.exception.CtgMqBaseException;
import com.ai.starter.factory.AIMQFactory;
import com.ai.starter.handler.AIMQProductHandler;
import com.ctg.mq.api.IMQPullConsumer;
import com.ctg.mq.api.IMQPushConsumer;
import com.ctg.mq.api.bean.ConsumeStatus;
import com.ctg.mq.api.bean.MQMessage;
import com.ctg.mq.api.bean.MQResult;
import com.ctg.mq.api.bean.MQSendResult;
import com.ctg.mq.api.exception.MQException;
import com.ctg.mq.api.listener.ConsumerTopicListener;
import com.ctg.mq.api.listener.ConsumerTopicStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CtgMqInterfaceImpl implements CtgMqInterface {

    @Autowired
    AIMQProductHandler aimqProductHandler;
    @Autowired
    AIMQFactory aimqFactory;

    @Override
    public void sendTest() {

        MQMessage message = new MQMessage("TEST1024",// topic CTGMQ_TEST 主题
                "ORDER_KEY_" + UUID.randomUUID(),// key 业务唯一性字段
                "ORDER_TAG",//tag  标签
                ("HELLO ORDER BODY" + UUID.randomUUID()).getBytes()// body  json 字节
        );
        try {
            MQSendResult sendResult = aimqProductHandler.sendMsg(message);
        } catch (CtgMqBaseException e) {
            //TODO  业务分析异常和处理
            e.printStackTrace();
        }finally {
            //TODO 应用最好在 容器销毁时，调用aimqFactory.close(); 关闭长连接
        }


    }

    @Override
    public void pullTest() {
        IMQPullConsumer pullConsumer = null;
        try {
            pullConsumer = aimqFactory.createPullConsumer();
        } catch (CtgMqBaseException e) {
            //TODO 业务处理获取连接失败的逻辑
            e.printStackTrace();
        }

        try {
            List<MQResult> mqResultList = pullConsumer.consumeMessagesByTopic("TEST1024", null, 2, 30000);
            for (MQResult result : mqResultList) {
                try {
                    System.out.println(result);
                    //业务处理正常，签收成功
                    pullConsumer.ackMessage(result, ConsumeStatus.CONSUME_SUCCESS);
                    //业务处理失败时，应签收失败，消息进入重试队列
                    //consumer.ackMessage(result, ConsumeStatus.RECONSUME_LATER);
                } catch (MQException e) {
                    //TODO 业务处理签收失败的逻辑
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            //TODO 业务处理消费的逻辑
            e.printStackTrace();
        } finally {

            //TODO 应用最好在 容器销毁时，调用aimqFactory.close(); 关闭长连接
        }
    }

    /**
     * 侦听式消费
     */
    @Override
    public void pushTest() {
        IMQPushConsumer pushConsumer = null;
        try {
            pushConsumer = aimqFactory.createPushConsumer();
        } catch (CtgMqBaseException e) {
            //TODO 业务处理获取连接失败的逻辑
            e.printStackTrace();
        }
        try {
            pushConsumer.listenTopic("CTGMQ_TEST_PUSH", null, new ConsumerTopicListener() {
                @Override
                public ConsumerTopicStatus onMessage(List<MQResult> mqResultList) {
                    for (MQResult result : mqResultList) {
                        System.out.println(result);
                    }
                    return ConsumerTopicStatus.CONSUME_SUCCESS;//对消息批量确认(成功)
                    //return ConsumerTopicStatus.RECONSUME_LATER;//对消息批量确认(失败)
                }
            });
        } catch (MQException e) {
            //TODO 业务处理侦听异常的逻辑
            e.printStackTrace();
        }finally {
            //TODO 应用最好在 容器销毁时，调用aimqFactory.close(); 关闭长连接 
        }

    }
}
