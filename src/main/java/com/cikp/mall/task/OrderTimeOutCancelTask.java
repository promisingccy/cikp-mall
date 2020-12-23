// package com.cikp.mall.task;
//
// /**
//  * @ClassName OrderTimeOutCancelTask
//  * @Description //订单超时取消并解锁库存的定时器
//  * @Author ccy
//  * @Date 2020/12/22 14:14
//  * @Version 1.0
//  **/
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;
//
// @Component
// public class OrderTimeOutCancelTask {
//     private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);
//
//     /**
//      * 每10分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
//      */
//     // @Scheduled(cron = "0 0/10 * ? * ?")
//     @Scheduled(cron = "0/10 * * ? * ?")
//     private void cancelTimeOutOrder() {
//         // TODO 调用取消订单的方法
//         LOGGER.info("取消订单，并根据sku编号释放锁定库存");
//     }
// }