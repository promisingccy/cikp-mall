package com.cikp.mall.mq.pattern02work;

/**
 * @ClassName WorkSender
 * @Description //TODO
 * @Author ccy
 * @Date 2021/1/5 10:37
 * @Version 1.0
 **/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkSender.class);

    @Autowired
    private RabbitTemplate template;

    private static final String queueName = "work.hello";

    /**
     [x] Sent 'Hello.1'
     [x] Sent 'Hello..2'
     [x] Sent 'Hello...3'
     [x] Sent 'Hello.4'
     [x] Sent 'Hello..5'
     [x] Sent 'Hello...6'
     [x] Sent 'Hello.7'
     [x] Sent 'Hello..8'
     [x] Sent 'Hello...9'
     [x] Sent 'Hello.10'
     */
    public void send(int index) {
        StringBuilder builder = new StringBuilder("Hello");
        int limitIndex = index % 3+1;
        for (int i = 0; i < limitIndex; i++) {
            builder.append('.');
        }
        builder.append(index+1);
        String message = builder.toString();
        template.convertAndSend(queueName, message);
        LOGGER.info(" [x] Sent '{}'", message);
    }

}

