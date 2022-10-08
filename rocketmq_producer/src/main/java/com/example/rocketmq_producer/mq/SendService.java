package com.example.rocketmq_producer.mq;

import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("source")
public class SendService {

    @Autowired
    Channels channels;

    @GetMapping("/send")
    public String send(@RequestParam Map<String, Object> params) {
        String msg = params.get("msg").toString();
        Message<String> message = MessageBuilder.withPayload(msg)
                .setHeader(RocketMQHeaders.TAGS,"tag1")
                .setHeader(RocketMQHeaders.KEYS,"key3")  //改成key3就不会被接收到消息
                .build();
        channels.producerChannelEmail().send(message);

        return "OK";
    }
}
