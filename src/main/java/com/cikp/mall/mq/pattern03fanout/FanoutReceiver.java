package com.cikp.mall.mq.pattern03fanout;

/**
 * @ClassName FanoutReceiver
 * @Description //TODO
 * @Author ccy
 * @Date 2021/1/5 11:33
 * @Version 1.0
 **/
import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class FanoutReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutReceiver.class);

    @RabbitListener(queues = "#{fanoutQueue1.name}")
    public void receive1(String in) {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{fanoutQueue2.name}")
    public void receive2(String in) {
        receive(in, 2);
    }

/**
 instance 1 [x] Received 'Hello.1'
 instance 1 [x] Done in 1.0008681s
 instance 1 [x] Received 'Hello..2'
 instance 1 [x] Done in 2.0008802s
 instance 1 [x] Received 'Hello...3'
 instance 1 [x] Done in 3.0018647s
 instance 1 [x] Received 'Hello.4'
 instance 1 [x] Done in 1.0001133s
 instance 1 [x] Received 'Hello..5'
 instance 1 [x] Done in 2.000355s
 instance 1 [x] Received 'Hello...6'
 instance 1 [x] Done in 3.0004258s
 instance 1 [x] Received 'Hello.7'
 instance 1 [x] Done in 1.000583s
 instance 1 [x] Received 'Hello..8'
 instance 1 [x] Done in 2.0008513s
 instance 1 [x] Received 'Hello...9'
 instance 1 [x] Done in 3.001667s
 instance 1 [x] Received 'Hello.10'
 instance 1 [x] Done in 1.000214s

 instance 2 [x] Received 'Hello.1'
 instance 2 [x] Done in 1.000889s
 instance 2 [x] Received 'Hello..2'
 instance 2 [x] Done in 2.0008502s
 instance 2 [x] Received 'Hello...3'
 instance 2 [x] Done in 3.0018064s
 instance 2 [x] Received 'Hello.4'
 instance 2 [x] Done in 1.0001335s
 instance 2 [x] Received 'Hello..5'
 instance 2 [x] Done in 2.0004617s
 instance 2 [x] Received 'Hello...6'
 instance 2 [x] Done in 3.0004716s
 instance 2 [x] Received 'Hello.7'
 instance 2 [x] Done in 1.0005157s
 instance 2 [x] Received 'Hello..8'
 instance 2 [x] Done in 2.0007362s
 instance 2 [x] Received 'Hello...9'
 instance 2 [x] Done in 3.0009694s
 instance 2 [x] Received 'Hello.10'
 instance 2 [x] Done in 1.0001307s
 */
    private void receive(String in, int receiver) {
        StopWatch watch = new StopWatch();
        watch.start();
        LOGGER.info("instance {} [x] Received '{}'", receiver, in);
        doWork(in);
        watch.stop();
        LOGGER.info("instance {} [x] Done in {}s", receiver, watch.getTotalTimeSeconds());
    }

    private void doWork(String in) {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                ThreadUtil.sleep(1000);
            }
        }
    }

}

