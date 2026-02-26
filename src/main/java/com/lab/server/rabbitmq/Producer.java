package com.lab.server.rabbitmq;

import com.lab.server.config.RabbitDirectConfig;
import com.lab.server.config.RabbitFanoutConfig;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;

    public Producer(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
    }

    public void send(String message) {
        this.amqpTemplate.convertAndSend("someQueue", message);
    }

    public void directSend(String message) {
        this.amqpTemplate.convertAndSend(RabbitDirectConfig.EXCHANGE_NAME, RabbitDirectConfig.ROUTING_KEY, message);
    }

    public void fanoutSend(String message) {
        this.amqpTemplate.convertAndSend(RabbitFanoutConfig.FANOUT_EXCHANGE, null, message);
    }
}
