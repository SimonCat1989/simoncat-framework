package com.simoncat.framework.redis;

import org.springframework.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisCacheConfig {

    @Bean
    public Cache redisCache() {
        return new RedisCache("cacheName");
    }
}
