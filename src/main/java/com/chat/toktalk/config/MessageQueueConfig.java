package com.chat.toktalk.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {
    public static final String QUEUE_NAME ="fanout_queue";
    public static final String EXCHANGE_NAME="fanout_exchange";
    @Bean
    Queue messageQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build() ;
    }
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding binding(Queue messageQueue,FanoutExchange fanoutExchange){
         return BindingBuilder.bind(messageQueue).to(fanoutExchange);
    }
}