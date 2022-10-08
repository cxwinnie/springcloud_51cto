package com.example.rocketmq_producer.mq;

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
        Message<String> message = MessageBuilder.withPayload(msg).build();
        channels.TopicEmail().send(message);

        return "OK";
    }
}
