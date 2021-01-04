package com.cikp.mall.mq;

/**
 * @ClassName SimpleRabbitConfig
 * @Description //TODO
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