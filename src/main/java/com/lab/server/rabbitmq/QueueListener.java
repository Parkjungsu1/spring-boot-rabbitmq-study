package com.lab.server.rabbitmq;

import com.lab.server.config.RabbitDirectConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueueListener {

    @RabbitListener(queues = "someQueue")
    public void processMessage(String message) {
        log.info("Received message: {}", message);
    }

    @RabbitListener(queues = RabbitDirectConfig.QUEUE_NAME)
    public void processDirectMessage(String message) {
        log.info("direct message: {}", message);
    }

    @RabbitListener(queues = "${rabbitmq.fanout.queue}")
    public void processFanoutMessage(String message) {
        log.info("fanout message: {}", message);
    }

    @RabbitListener(queues = "${rabbitmq.topic.queue}")
    public void processTopicMessage(String message) {
        log.info("topic message: {}", message);
    }

    @RabbitListener(queues = "${rabbitmq.headers.queue}")
    public void processHeadersMessage(String message) {
        log.info("headers message: {}", message);
    }
}
