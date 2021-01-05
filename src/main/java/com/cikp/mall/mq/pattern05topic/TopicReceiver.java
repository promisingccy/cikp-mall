package com.cikp.mall.mq.pattern05topic;

/**
 * @ClassName TopicReceiver
 * @Description //TODO
 * @Author ccy
 * @Date 2021/1/5 13:44
 * @Version 1.0
 **/
import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class TopicReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicReceiver.class);

    @RabbitListener(queues = "#{topicQueue1.name}")
    public void receive1(String in){
        receive(in, 1);
    }

    @RabbitListener(queues = "#{topicQueue2.name}")
    public void receive2(String in){
        receive(in, 2);
    }

/**
 instance 1 [x] Received 'Hello to quick.orange.rabbit 1'
 instance 1 [x] Received 'Hello to lazy.orange.elephant 2'
 instance 1 [x] Received 'Hello to quick.orange.fox 3'
 instance 1 [x] Received 'Hello to lazy.pink.rabbit 5'
 instance 1 [x] Received 'Hello to quick.orange.rabbit 7'
 instance 1 [x] Received 'Hello to lazy.orange.elephant 8'
 instance 1 [x] Received 'Hello to quick.orange.fox 9'

 instance 2 [x] Received 'Hello to lazy.orange.elephant 2'
 instance 2 [x] Received 'Hello to lazy.brown.fox 4'
 instance 2 [x] Received 'Hello to lazy.pink.rabbit 5'
 instance 2 [x] Received 'Hello to lazy.orange.elephant 8'
 instance 2 [x] Received 'Hello to lazy.brown.fox 10'
 */
    public void receive(String in, int receiver){
        StopWatch watch = new StopWatch();
        watch.start();
        LOGGER.info("instance {} [x] Received '{}'", receiver, in);
        doWork(in);
        watch.stop();
        LOGGER.info("instance {} [x] Done in {}s", receiver, watch.getTotalTimeSeconds());
    }

    private void doWork(String in){
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                ThreadUtil.sleep(1000);
            }
        }
    }

}

