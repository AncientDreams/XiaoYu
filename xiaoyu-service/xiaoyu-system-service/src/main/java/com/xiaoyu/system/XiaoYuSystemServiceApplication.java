package com.xiaoyu.system;

import com.xiaoyu.common.core.constant.AppConstant;
import com.xiaoyu.user.fallback.UserFallbackFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 *
 * @author zxy
 */
@SpringBootApplication()
@EnableDiscoveryClient
@MapperScan(AppConstant.SYSTEM_SERVICE_MAPPER)
@EnableFeignClients(basePackages = {AppConstant.USER_SERVICE_FEIGN_PACKAGES})
@Import(UserFallbackFactory.class)
public class XiaoYuSystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoYuSystemServiceApplication.class, args);
    }

}
