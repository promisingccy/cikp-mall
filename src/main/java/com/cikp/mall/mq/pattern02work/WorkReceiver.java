package com.cikp.mall.mq.pattern02work;

/**
 * @ClassName WorkReceiver
 * @Description //TODO
 * @Author ccy
 * @Date 2021/1/5 10:36
 * @Version 1.0
 **/
import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "work.hello")
public class WorkReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkReceiver.class);

    private final int instance;

    public WorkReceiver(int i) {
        this.instance = i;
    }

    /**
     instance 1 [x] Received 'Hello.1'
     instance 1 [x] Done in 1.0004507s
     instance 1 [x] Received 'Hello...3'
     instance 1 [x] Done in 3.0017595s
     instance 1 [x] Received 'Hello..5'
     instance 1 [x] Done in 2.0009037s
     instance 1 [x] Received 'Hello.7'
     instance 1 [x] Done in 1.0067927s
     instance 1 [x] Received 'Hello...9'
     instance 1 [x] Done in 3.0010921s

     instance 2 [x] Received 'Hello..2'
     instance 2 [x] Done in 2.0010786s
     instance 2 [x] Received 'Hello.4'
     instance 2 [x] Done in 1.000441s
     instance 2 [x] Received 'Hello...6'
     instance 2 [x] Done in 3.0068814s
     instance 2 [x] Received 'Hello..8'
     instance 2 [x] Done in 2.0003371s
     instance 2 [x] Received 'Hello.10'
     instance 2 [x] Done in 1.000945s
     */
    @RabbitHandler
    public void receive(String in) {
        StopWatch watch = new StopWatch();
        watch.start();
        LOGGER.info("instance {} [x] Received '{}'", this.instance, in);
        doWork(in);
        watch.stop();
        LOGGER.info("instance {} [x] Done in {}s", this.instance, watch.getTotalTimeSeconds());
    }


    private void doWork(String in) {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                ThreadUtil.sleep(1000);
            }
        }
    }

}
