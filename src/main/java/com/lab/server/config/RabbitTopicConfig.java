package com.lab.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {
    // 다이렉트처럼 routing key를 보고 판단
    // 다만, 다른점은 Topic은 주소의 패턴을 보고 매칭시켜줌
    // *  - 단어 딱 1개를 대신 예) *.amqp.* -> 중간에 amqp가 들어간 라우우팅 키의 메시지만 받음
    // #  - 단어 0개 이상 포함 예) amqp.# -> amqp로 시작하는 라우팅 키의 메시지만 받음
    @Value("${rabbitmq.topic.exchange}")
    private String TOPIC_EXCHANGE;

    public String getExchange() {
        return TOPIC_EXCHANGE;
    }
    @Value("${rabbitmq.topic.queue}")
    private String TOPIC_QUEUE;

    @Value("${rabbitmq.topic.key}")
    private String TOPIC_KEY;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue(TOPIC_QUEUE, true, false, true);
    }

    //!! 큐와 exchange 사이에 어떤 라우팅 키로 바운딩 시킬지 중요 !!!
    //바인딩 다수
    @Bean
    public Binding TopicBinding_1(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(TOPIC_KEY);
    }

    @Bean
    public Binding TopicBinding_2(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("topic.*");
    }
}
