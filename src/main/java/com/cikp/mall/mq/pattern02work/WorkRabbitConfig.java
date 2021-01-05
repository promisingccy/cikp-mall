package com.cikp.mall.mq.pattern02work;

/**
 * @ClassName WorkRabbitConfig
 * @Description //工作模式-WorkRabbitConfig-互相竞争
 * 工作模式是指向多个互相竞争的消费者发送消息的模式，它包含一个生产者、两个消费者和一个队列。
 * 两个消费者同时绑定到一个队列上去，当消费者获取消息处理耗时任务时，空闲的消费者从队列中获取并消费消息。
 * @Author ccy
 * @Date 2021/1/5 10:36
 * @Version 1.0
 **/
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkRabbitConfig {

    @Bean
    public Queue workQueue() {
        return new Queue("work.hello");
    }

    @Bean
    public WorkReceiver workReceiver1() {
        //消费者1
        return new WorkReceiver(1);
    }

    @Bean
    public WorkReceiver workReceiver2() {
        //消费者2
        return new WorkReceiver(2);
    }

    @Bean
    public WorkSender workSender() {
        //生产者
        return new WorkSender();
    }

}
