package com.l.library.sysadmin.organization.config;

import com.l.library.common.web.redis.RedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
public class MyRedisConfig extends RedisConfig {
}
