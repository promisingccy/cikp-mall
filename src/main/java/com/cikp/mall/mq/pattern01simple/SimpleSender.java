package com.cikp.mall.mq.pattern01simple;

/**
 * @ClassName SimpleSender
 * @Description //TODO
 * @Author ccy
 * @Date 2021/1/4 17:38
 * @Version 1.0
 **/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSender.class);

    @Autowired
    private RabbitTemplate template;

    private static final String queueName="simple.hello";

    /**
     [x] Sent 'Hello World!'
     [x] Sent 'Hello World!'
     [x] Sent 'Hello World!'
     [x] Sent 'Hello World!'
     [x] Sent 'Hello World!'
     [x] Sent 'Hello World!'
     [x] Sent 'Hello World!'
     */
    public void send() {
        String message = "Hello World!";
        this.template.convertAndSend(queueName, message);
        LOGGER.info(" [x] Sent '{}'", message);
    }

}
