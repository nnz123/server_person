package com.ccbcfx.learn.configs;


import com.ccbcfx.learn.constant.StaffConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class RabbitConfig {
    @Bean
    public Queue getQueue(){
        return new Queue(StaffConstant.QUEUE_NAME,false);
    }
}
