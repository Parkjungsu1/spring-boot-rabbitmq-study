package com.lab.server.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableRabbit
@Component
public class RabbitConfig {
    //참조 : https://docs.spring.io/spring-boot/reference/messaging/amqp.html

    //기본 설정
    //RabbitMQ는 기본적으로 이름 없는 exchange가 존재
    //Routing Key는 큐의 이름과 동일하게 생성됨
    @Bean
    public Queue myQueue() {
        return new Queue("someQueue", false);
    }
}
