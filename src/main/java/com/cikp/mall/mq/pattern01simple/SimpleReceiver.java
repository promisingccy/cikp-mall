package com.cikp.mall.mq.pattern01simple;

/**
 * @ClassName SimpleReceiver
 * @Description //TODO
 * @Author ccy
 * @Date 2021/1/4 17:37
 * @Version 1.0
 **/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "simple.hello")
public class SimpleReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleReceiver.class);

    /**
     [x] Received 'Hello World!'
     [x] Received 'Hello World!'
     [x] Received 'Hello World!'
     [x] Received 'Hello World!'
     [x] Received 'Hello World!'
     [x] Received 'Hello World!'
     [x] Received 'Hello World!'
     */
    @RabbitHandler
    public void receive(String in) {
        LOGGER.info(" [x] Received '{}'", in);
    }

}
