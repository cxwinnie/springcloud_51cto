package com.example.rocketmq_producer.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    @Output("TopicEmail")
    MessageChannel TopicEmail();
}
