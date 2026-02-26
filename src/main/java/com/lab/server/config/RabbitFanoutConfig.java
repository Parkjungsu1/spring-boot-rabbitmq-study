package com.lab.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RabbitFanoutConfig {

    public static final String FANOUT_EXCHANGE = "fanout.exchange";
    public static final String FANOUT_QUEUE = "fanout.queue.2";
    public static final String FANOUT_ROUTING_KEY = "fanout.routing.key";

    // 1. Exchange 등록 (fanout타입)
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    // 2. 큐 등록
    @Bean
    public Queue fanoutQueue() {
        return new Queue(FANOUT_QUEUE);
    }

    // 3. 바인딩 (Routing Key가 필요 없음!)
    @Bean
    public Binding binding(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }
}
