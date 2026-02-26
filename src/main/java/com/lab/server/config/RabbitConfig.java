package com.lab.server.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    //참조 : https://docs.spring.io/spring-boot/reference/messaging/amqp.html

    //기본 설정
    //RabbitMQ는 기본적으로 이름 없는 exchange가 존재
    //Routing Key는 큐의 이름과 동일하게 생성됨
    //보통 바인딩 작업은 Consumer(Lisnter) 서비스에서 수행 - 자기 우편함은 자기가 관리한다는 의미

    // durable false : 브로커 재시작 시 유지 안됨
    // exclusive false : 특정 커넥션 전용 아님
    // autoDelete false : 마지막 consumer가 끊기면 자동 삭제
    @Bean
    public Queue myQueue() {
        return new Queue("someQueue", false);
    }
}
