package com.cikp.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.cikp.mall.mybatisFile.mapper")
public class MyBatisConfig {
}
