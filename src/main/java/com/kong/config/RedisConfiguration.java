package com.kong.config;/**
 * Created by fanbeibei on 2017/9/15.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Fan Beibei
 * @Description: reids 配置
 * @date 2017/9/15 16:53
 */
@Configuration
public class RedisConfiguration {

    @Autowired
    private StringRedisTemplate template;

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        return template;
    }

//    @Bean
//    public CacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        return cacheManager;
//    }
//
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new RedisObjectSerializer());
//        return template;
//    }

}

