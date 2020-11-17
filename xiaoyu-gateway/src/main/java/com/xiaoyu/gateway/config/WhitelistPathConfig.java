package com.xiaoyu.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 配置网关访问白名单，指定路径不做拦截。
 * 配置到 Nacos 中
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
        urls.add("/**/v2/api-docs");
        urls.add("/**/doc.html");
        urls.add("/**/webjars/**");
        urls.add("/**/swagger-resources/**");
    }
}
