package com.xiaoyu.common.redis;


import com.xiaoyu.common.redis.config.RedisCacheManagerConfig;
import com.xiaoyu.common.redis.handler.RedisHandler;
import lombok.AllArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;


/**
 * <p>
 * Redis 自动装配
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020-09-17 14:21
 */
@Configuration
@AllArgsConstructor
@Import({RedisCacheManagerConfig.class})
public class RedisAutoConfiguration {

    private final RedisTemplate redisTemplate;

    private final RedisProperties redisProperties;

    @Bean
    public RedisHandler redisHandler() {
        return new RedisHandler(redisTemplate);
    }

    /**
     * redis 单机模式配置 Redisson
     */
    @Bean
    public RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setRetryInterval(5000)
                .setTimeout(10000)
                .setConnectTimeout(10000);
        if (!StringUtils.isEmpty(redisProperties.getPassword())) {
            config.useSingleServer().setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }


//    /**
//     * redis 主从  集群 配置 Redisson
//     */
//    @Bean
//    public RedissonClient getRedisson() {
//        Config config = new Config();
//        config.useMasterSlaveServers()
//                .setMasterAddress("redis://***(主服务器IP):6379").setPassword("web2017")
//                .addSlaveAddress("redis://***（从服务器IP）:6379")
//                .setRetryInterval(5000)
//                .setTimeout(10000)
//                .setConnectTimeout(10000);//（连接超时，单位：毫秒 默认值：3000）;
//
//        //  哨兵模式config.useSentinelServers().setMasterName("mymaster").setPassword("web2017").addSentinelAddress("***(哨兵IP):26379", "***(哨兵IP):26379", "***(哨兵IP):26380");
//        return Redisson.create(config);
//    }
}