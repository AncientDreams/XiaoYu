package com.xiaoyu.common.core.handler;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

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

    private final NacosConfigProperties nacosConfigProperties;

    private final ConfigurableEnvironment configurableEnvironment;

    @Override
    public void run(ApplicationArguments args) {
        String content;
        try {
            StringBuilder outLog = new StringBuilder("\n##############################   Nacos 配置参数打印开始   ##############################\n");
            String serverAddr = nacosConfigProperties.getServerAddr().split(":")[0];
            String applicationNameConfig = "spring.application.name";
            String groupNameConfig = "spring.cloud.nacos.config.group";
            //指定Nacos 配置中心的主配置名称, 约定好名称:   ${spring.application.name}.yaml
            String applicationName = configurableEnvironment.getProperty(applicationNameConfig);
            String dataId = Objects.requireNonNull(applicationName).concat(".yaml");

            //分组名称
            String group = configurableEnvironment.getProperty(groupNameConfig);
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            content = configService.getConfig(dataId, group, 5000);
            outLog.append(content).append("\n");

            //读取环境配置文件，名称约定  ${spring.application.name}-${spring.profiles.active}.yaml
            String active = "spring.profiles.active";
            String activeDataId = configurableEnvironment.getProperty(active);
            if (!StringUtils.isEmpty(activeDataId)) {
                String activeContent = configService.getConfig(applicationName + "-" + activeDataId + ".yaml", group, 5000);
                outLog.append(activeContent == null ? "" : activeContent).append("\n");
            }

            //读取共享的配置文件
            String sharedConfigs = "spring.cloud.nacos.config.shared-configs";
            String sharedDataId = configurableEnvironment.getProperty(sharedConfigs);
            StringBuilder shardContent = new StringBuilder();
            if (sharedDataId != null) {
                //读取多个共享文件
                String[] configs = sharedDataId.split(",");
                for (String config : configs) {
                    shardContent.append("\n").append(configService.getConfig(config, group, 5000));
                }
            }

            outLog.append(shardContent).
                    append("\n##############################   Nacos 配置参数打印结束   ##############################\n");
            logger.info(outLog);
        } catch (Exception e) {
            logger.error("Nacos 启动配置输出错误", e);
        }
    }

}
