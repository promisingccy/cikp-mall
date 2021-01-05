package com.cikp.mall.mq.pattern04direct;

/**
 * @ClassName DirectReceiver
 * @Description //TODO
 * @Author ccy
 * @Date 2021/1/5 11:51
 * @Version 1.0
 **/
import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class DirectReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectReceiver.class);

    @RabbitListener(queues = "#{directQueue1.name}")
    public void receive1(String in){
        receive(in, 1);
    }

    @RabbitListener(queues = "#{directQueue2.name}")
    public void receive2(String in){
        receive(in, 2);
    }

/**
 instance 1 [x] Received 'Hello to orange 1'
 instance 1 [x] Done in 3.614E-4s
 instance 1 [x] Received 'Hello to black 2'
 instance 1 [x] Done in 1.851E-4s
 instance 1 [x] Received 'Hello to orange 4'
 instance 1 [x] Done in 1.212E-4s
 instance 1 [x] Received 'Hello to black 5'
 instance 1 [x] Done in 1.599E-4s
 instance 1 [x] Received 'Hello to orange 7'
 instance 1 [x] Done in 2.51E-4s
 instance 1 [x] Received 'Hello to black 8'
 instance 1 [x] Done in 2.59E-4s
 instance 1 [x] Received 'Hello to orange 10'
 instance 1 [x] Done in 3.809E-4s

 instance 2 [x] Received 'Hello to black 2'
 instance 2 [x] Done in 1.48E-4s
 instance 2 [x] Received 'Hello to green 3'
 instance 2 [x] Done in 1.589E-4s
 instance 2 [x] Received 'Hello to black 5'
 instance 2 [x] Done in 1.783E-4s
 instance 2 [x] Received 'Hello to green 6'
 instance 2 [x] Done in 2.286E-4s
 instance 2 [x] Received 'Hello to black 8'
 instance 2 [x] Done in 2.912E-4s
 instance 2 [x] Received 'Hello to green 9'
 instance 2 [x] Done in 2.9E-4s
 */
    private void receive(String in, int receiver){
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
