package com.lab.server.rabbitmq;

import com.lab.server.config.RabbitDirectConfig;
import com.lab.server.config.RabbitFanoutConfig;
import com.lab.server.config.RabbitHeadersConfig;
import com.lab.server.config.RabbitTopicConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {

    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;
    private final RabbitFanoutConfig rabbitFanoutConfig;
    private final RabbitTopicConfig rabbitTopicConfig;
    private final RabbitHeadersConfig rabbitHeadersConfig;

    public void send(String message) {
        this.amqpTemplate.convertAndSend("someQueue", message);
    }

    public void directSend(String message) {
        this.amqpTemplate.convertAndSend(RabbitDirectConfig.EXCHANGE_NAME, RabbitDirectConfig.ROUTING_KEY, message);
    }

    public void fanoutSend(String message) {
        this.amqpTemplate.convertAndSend(rabbitFanoutConfig.getExchange(), null, message);
    }

    public void topicSend(String message, String routingKey) {
        this.amqpTemplate.convertAndSend(rabbitTopicConfig.getExchange(), routingKey ,message);
    }

    public void headerSend(String message, String value) {
        //1. 헤더 정보를 담을 속성 객체 만들어주기
        MessageProperties properties = new MessageProperties();
        properties.setHeader("key", value);
        // 2. 본문 데이터와 합쳐줘야함
        Message msg = new Message(message.getBytes(), properties);
        // 3, headers는 라우팅 키를 무시함 따라서 공백으로 넣어도 괜찮다.
        this.amqpTemplate.convertAndSend(rabbitHeadersConfig.getExchange(), null, msg);
    }
}
