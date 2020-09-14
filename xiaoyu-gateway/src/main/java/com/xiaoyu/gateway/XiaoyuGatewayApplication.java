package com.xiaoyu.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class XiaoyuGatewayApplication {

    /**
     * 统一入口,
     * 用户认证,
     * 用户鉴权,
     * 权限校验,
     * 黑名单和白名单:
     * 动态黑名单和动态白名单:
     * 接口限流,
     * 服务熔断,
     * 请求过滤,
     * 请求转发,
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(XiaoyuGatewayApplication.class, args);
    }

}
