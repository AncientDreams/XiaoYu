package com.xiaoyu.common.job.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * XxlJob配置属性
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/10/12 17:36
 */
@Configuration
@ConfigurationProperties(prefix = XxlJobExecutorProperties.PREFIX)
@Data
public class XxlJobExecutorProperties {

    final static String PREFIX = "xxl.job.executor";

    private String appname;
    private String address;
    private String ip;
    private int port;
    private String logpath;
    private int logretentiondays;
}
