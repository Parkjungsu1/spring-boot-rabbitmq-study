package com.lab.server.rabbitmq;

import com.lab.server.config.RabbitDirectConfig;
import com.lab.server.config.RabbitFanoutConfig;
import com.lab.server.config.RabbitTopicConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {

    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;
    private final RabbitFanoutConfig rabbitFanoutConfig;
    private final RabbitTopicConfig rabbitTopicConfig;

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
}
