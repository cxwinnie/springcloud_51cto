package com.example.rocketmq_consumer.mq;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiverService {

    @StreamListener("TopicEmail")
    public void receiver(Object obj) {
        System.out.println("接收到的消息是：" + obj);
    }
}
