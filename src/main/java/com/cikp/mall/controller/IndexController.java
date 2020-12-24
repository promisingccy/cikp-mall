package com.cikp.mall.controller;

import com.cikp.mall.common.api.CommonResult;
import com.cikp.mall.dto.HelloDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName IndexController
 * @Description //测试控制器
 * @Author ccy
 * @Date 2020/12/24 15:39
 * @Version 1.0
 **/
@RestController
@RequestMapping("/index")
public class IndexController {
    @ApiOperation("index")
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public CommonResult index(@Valid @RequestBody HelloDto dto) {
        return CommonResult.success("aaa");
    }
}
