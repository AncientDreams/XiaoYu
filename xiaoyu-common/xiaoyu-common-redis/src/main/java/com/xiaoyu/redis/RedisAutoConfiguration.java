package com.xiaoyu.redis;


import com.xiaoyu.redis.config.RedisCacheManagerConfig;
import com.xiaoyu.redis.handler.RedisHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;

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

    @Bean
    public RedisHandler redisHandler() {
        return new RedisHandler(redisTemplate);
    }

}
