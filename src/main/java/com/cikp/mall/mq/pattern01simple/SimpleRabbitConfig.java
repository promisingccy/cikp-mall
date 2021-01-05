package com.cikp.mall.mq.pattern01simple;

/**
 * @ClassName SimpleRabbitConfig
 * @Description //简单模式
 * 简单模式是最简单的消息模式，它包含一个生产者、一个消费者和一个队列。
 * 生产者向队列里发送消息，消费者从队列中获取消息并消费。
 * @Author ccy
 * @Date 2021/1/4 17:35
 * @Version 1.0
 **/
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleRabbitConfig {

    @Bean
    public Queue hello() {
        return new Queue("simple.hello");
    }

    @Bean
    public SimpleSender simpleSender(){
        return new SimpleSender();
    }

    @Bean
    public SimpleReceiver simpleReceiver(){
        return new SimpleReceiver();
    }

}