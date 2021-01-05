package com.cikp.mall.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.cikp.mall.common.api.CommonResult;
import com.cikp.mall.mq.pattern01simple.SimpleSender;
import com.cikp.mall.mq.pattern02work.WorkSender;
import com.cikp.mall.mq.pattern03fanout.FanoutSender;
import com.cikp.mall.mq.pattern04direct.DirectSender;
import com.cikp.mall.mq.pattern05topic.TopicSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RabbitController
 * @Description //TODO
 * @Author ccy
 * @Date 2021/1/4 17:40
 * @Version 1.0
 **/
@Api(tags = "RabbitController", description = "RabbitMQ功能测试")
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private SimpleSender simpleSender;
    @Autowired
    private WorkSender workSender;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private DirectSender directSender;
    @Autowired
    private TopicSender topicSender;

    @ApiOperation("简单模式")
    @GetMapping("/simple")
    public CommonResult simpleTest() {
        for(int i=0;i<10;i++){
            simpleSender.send();
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation("工作模式")
    @GetMapping(value = "/work")
    public CommonResult workTest() {
        for(int i=0;i<10;i++){
            workSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation("发布/订阅模式")
    @GetMapping(value = "/fanout")
    public CommonResult fanoutTest() {
        for(int i=0;i<10;i++){
            fanoutSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation("路由模式")
    @GetMapping(value = "/direct")
    public CommonResult directTest() {
        for(int i=0;i<10;i++){
            directSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation("通配符模式")
    @GetMapping(value = "/topic")
    public CommonResult topicTest() {
        for(int i=0;i<10;i++){
            topicSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }
}
