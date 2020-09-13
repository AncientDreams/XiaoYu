package com.xiaoyu.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoyu.auth.mapper")
public class XiaoyuAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoyuAuthApplication.class, args);
    }

}
