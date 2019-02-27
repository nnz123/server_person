package com.ccbcfx.learn.mq;

import com.ccbcfx.learn.constant.StaffConstant;
import com.ccbcfx.learn.message.PersonMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = StaffConstant.QUEUE_NAME)
public class PersonReceiver {
    @RabbitHandler
    public void process(@Payload PersonMessage message, @Headers Map<String,Object> headers, Channel channel){
        System.out.println(message.toString());
        try {
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG),false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
