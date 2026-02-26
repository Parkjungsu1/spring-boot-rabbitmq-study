package com.lab.server.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitHeadersConfig {
    //라우팅 키를ㄹ 아예 무시한다.
    //대신 메시지 보낼 때 같이 보내는 Header의 정보를 보고 전달함.

    @Value("${rabbitmq.headers.exchange}")
    private String HEADERS_EXCHANGE;

    public String getExchange() {
        return HEADERS_EXCHANGE;
    }

    @Value("${rabbitmq.headers.queue}")
    private String HEADERS_QUEUE;

    @Bean
    public Queue headersQueue() {
        return new Queue(HEADERS_QUEUE, true, false, true);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE);
    }


    @Bean
    public Binding headersBinding(Queue headersQueue, HeadersExchange headersExchange) {
        return BindingBuilder.bind(headersQueue)
        .to(headersExchange)
        .where("key").matches("1");
    }
}
