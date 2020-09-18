package com.xiaoyu.gateway;


import constant.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 微服务网关启动类
 *
 * @author zxy
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {AppConstant.USER_SERVICE_FEIGN_PACKAGES})
public class XiaoYuGatewayApplication {

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
        SpringApplication.run(XiaoYuGatewayApplication.class, args);
    }

}
