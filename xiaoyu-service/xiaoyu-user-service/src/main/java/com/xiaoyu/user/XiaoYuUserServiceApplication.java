package com.xiaoyu.user;

import com.xiaoyu.common.core.constant.AppConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 用户服务启动类
 *
 * @author zxy
 */
@SpringBootApplication
@MapperScan(AppConstant.USER_SERVICE_MAPPER)
@EnableDiscoveryClient
public class XiaoYuUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoYuUserServiceApplication.class, args);
    }

}
