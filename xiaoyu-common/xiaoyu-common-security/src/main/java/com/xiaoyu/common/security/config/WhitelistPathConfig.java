package com.xiaoyu.common.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 白名单访问url，无需通过网关鉴权可以直接访问。
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/8/31 15:20
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = WhitelistPathConfig.PREFIX)
public class WhitelistPathConfig {

    static final String PREFIX = "ignore";

    /**
     * 白名单 Url
     */
    private List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
        urls.add("/swagger-ui/**");
        urls.add("/actuator/**");
        urls.add("/**/v2/api-docs");
        urls.add("/v2/api-docs");
    }
}
