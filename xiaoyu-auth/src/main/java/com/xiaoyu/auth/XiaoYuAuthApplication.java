package com.xiaoyu.auth;

import constant.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 微服务权限认证服务启动类
 *
 * @author zxy
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {AppConstant.USER_SERVICE_FEIGN_PACKAGES})
public class XiaoYuAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoYuAuthApplication.class, args);
    }

}
