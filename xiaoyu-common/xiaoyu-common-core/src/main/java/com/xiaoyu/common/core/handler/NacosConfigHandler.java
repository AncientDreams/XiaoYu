package com.xiaoyu.common.core.handler;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Objects;
import java.util.Properties;

/**
 * <p>
 * Nacos 启动配置输出
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/28 16:09
 */
@AllArgsConstructor
public class NacosConfigHandler implements ApplicationRunner {

    private final Log logger = LogFactory.getLog(this.getClass());

    private NacosConfigProperties nacosConfigProperties;

    private ConfigurableEnvironment configurableEnvironment;

    @Override
    public void run(ApplicationArguments args) {
        String content;
        try {
            StringBuilder outLog = new StringBuilder("\n##############################   Nacos 配置参数打印开始   ##############################\n");
            String serverAddr = nacosConfigProperties.getServerAddr().split(":")[0];
            String applicationName = "spring.application.name";
            //指定Nacos 配置中心的配置名称, 约定好名称:    application name  + .yaml
            String dataId = Objects.requireNonNull(configurableEnvironment.getProperty(applicationName)).concat(".yaml");
            //默认
            String group = "DEFAULT_GROUP";
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            content = configService.getConfig(dataId, group, 5000);
            if(content == null){
                return;
            }
            outLog.append(content).append("\n##############################   Nacos 配置参数打印结束   ##############################\n");
            logger.info(outLog);
        } catch (Exception e) {
            logger.error("Nacos 启动配置输出错误", e);
        }
    }

}
