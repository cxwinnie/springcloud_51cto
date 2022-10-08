package com.example.rocketmq_consumer.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    @Input
    SubscribableChannel TopicEmail();
}
