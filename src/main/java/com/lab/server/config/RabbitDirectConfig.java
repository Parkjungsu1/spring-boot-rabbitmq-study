package com.lab.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableRabbit
@Component
public class RabbitDirectConfig {
    public static final String QUEUE_NAME = "direct.queue";
    public static final String EXCHANGE_NAME = "direct.exchange";
    public static final String ROUTING_KEY = "direct.key";

    // 1. direct Queue setting
    @Bean
    public Queue directQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    // 2. direct exchange 등록
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    //3. 바인딩 (queue 와 exchange를 키로 연결)
    @Bean
    public Binding directBinding(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with(ROUTING_KEY);
    }
}
