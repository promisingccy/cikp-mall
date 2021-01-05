package com.cikp.mall.mq.pattern04direct;

/**
 * @ClassName DirectSender
 * @Description //TODO
 * @Author ccy
 * @Date 2021/1/5 11:50
 * @Version 1.0
 **/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class DirectSender {

    @Autowired
    private RabbitTemplate template;

    private static final String exchangeName = "exchange.direct";

    private final String[] keys = {"orange", "black", "green"};

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectSender.class);

    /**
     [x] Sent 'Hello to orange 1'
     [x] Sent 'Hello to black 2'
     [x] Sent 'Hello to green 3'
     [x] Sent 'Hello to orange 4'
     [x] Sent 'Hello to black 5'
     [x] Sent 'Hello to green 6'
     [x] Sent 'Hello to orange 7'
     [x] Sent 'Hello to black 8'
     [x] Sent 'Hello to green 9'
     [x] Sent 'Hello to orange 10'
     */
    public void send(int index) {
        StringBuilder builder = new StringBuilder("Hello to ");
        int limitIndex = index % 3;
        String key = keys[limitIndex];
        builder.append(key).append(' ');
        builder.append(index+1);
        String message = builder.toString();
        template.convertAndSend(exchangeName, key, message);
        LOGGER.info(" [x] Sent '{}'", message);
    }

}
