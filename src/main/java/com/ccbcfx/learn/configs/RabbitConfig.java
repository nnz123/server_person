package com.ccbcfx.learn.configs;

import com.ccbcfx.learn.constant.StaffConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: 陆志庆
 * @CreateDate: 2019/3/4 18:32
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue getQueue() {
        Queue queue = new Queue(StaffConstant.QUEUE_NAME, false);
        return queue;
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(StaffConstant.EXCHANGE);
    }

    @Bean
    Binding bindingExchangeA(Queue Queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(Queue).to(fanoutExchange);
    }

    RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate();
        template.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause)->{
            if(ack){
                System.out.println("消息发送成功");
            }else {
                System.out.println("消息发送失败"+correlationData.toString()+cause);
            }

        });
        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println(message.toString()+replyCode+replyText+exchange+routingKey);
            }
        });
        return template;
    }
}
