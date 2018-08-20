package com.springboot.swagger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
// 扫描mapper
@MapperScan("com.springboot.swagger.pojo")
//开启定时任务
@EnableScheduling
//开启异步调用方法
@EnableAsync
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }

}
