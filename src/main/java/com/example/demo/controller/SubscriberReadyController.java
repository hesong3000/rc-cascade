package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.MQConstant;
import com.example.demo.task.MCUSubscriberReady;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriberReadyController {
    @RequestMapping("/subscriberready")
    @ResponseBody
    private String subscriberReady(){
        JSONObject request_msg = new JSONObject();
        request_msg.put("type", MCUSubscriberReady.taskType);
        request_msg.put("client_id", "client_2");
        request_msg.put("publish_stream_id", "stream_client_1_publish");
        request_msg.put("stream_id", "subscribe_client_1_publish");
        request_msg.put("mcu_id", "mcu_1");

        rabbitTemplate.convertAndSend(MQConstant.MQ_EXCHANGE, MQConstant.MQ_RC_BINDING_KEY, request_msg);
        return "OK";
    }

    @Autowired
    private AmqpTemplate rabbitTemplate;
}
