package com.lab.server.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;

    // 문서 예제 그대로의 생성자 주입입니다.
    public Producer(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
    }

    public void send(String message) {
        this.amqpTemplate.convertAndSend("someQueue", message);
    }
}
