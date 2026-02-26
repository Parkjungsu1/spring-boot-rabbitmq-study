package com.lab.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitFanoutConfig {

    //exchange만 보고 브로드 캐스팅
    @Value("${rabbitmq.fanout.exchange}")
    public String FANOUT_EXCHANGE;

    public String getExchange() {
        return FANOUT_EXCHANGE;
    }

    @Value("${rabbitmq.fanout.queue}")
    private String FANOUT_QUEUE;

    // 1. Exchange 등록 (fanout타입)
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    // 2. 큐 등록
    @Bean
    public Queue fanoutQueue() {
        return new Queue(FANOUT_QUEUE, true, false, true);
    }

    // 3. 바인딩 (Routing Key가 필요 없음!)
    @Bean
    public Binding fanoutBinding(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }
}
