package com.xiaoyu.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author zxy
 */
@SpringBootApplication
@EnableDiscoveryClient
public class XiaoYuGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoYuGatewayApplication.class, args);
    }

}
