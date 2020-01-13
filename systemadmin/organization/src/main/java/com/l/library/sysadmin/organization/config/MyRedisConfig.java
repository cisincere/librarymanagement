package com.l.library.sysadmin.organization.config;

import com.l.library.common.web.redis.RedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import sun.plugin.dom.core.Document;

@Configuration
@EnableCaching
public class MyRedisConfig extends RedisConfig {
}
