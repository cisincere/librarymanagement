package com.l.library.auth.authentication.config;

import com.l.library.auth.authentication.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Map;

@Component
public class LoadResourceDefine {

    @Autowired
    private IResourceService resourceService;

    @Bean
    public Map<RequestMatcher, ConfigAttribute> requestMatcherConfigAttributeMap(){
        return resourceService.loadResource();
    }
}
