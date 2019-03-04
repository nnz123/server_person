package com.ccbcfx.learn.mq;

import com.ccbcfx.learn.constant.StaffConstant;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonSender {
    @Autowired
    private RabbitTemplate template;

    public void send(Object message){
        this.template.convertAndSend(StaffConstant.QUEUE_NAME,message);
    }
}
