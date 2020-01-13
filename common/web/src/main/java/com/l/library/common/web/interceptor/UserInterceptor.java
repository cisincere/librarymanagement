package com.l.library.common.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.l.library.manangement.common.core.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    /**
     * 服务间调用token用户信息，格式为json
     * {
     *     "user_name":.... 必须要,
     *     "自定义key": "value"
     * }
     */
    public static final String X_CLIENT_TOKEN_USER = "x-client-token-user";
    /**
     * 服务器间调用的认证token
     */
    public static final String X_CLIENT_TOKEN = "x-client-token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从网关获取并校验,通过校验就可信任x-client-token-user中的信息
        checkToken(request.getHeader(X_CLIENT_TOKEN));
        String userInfoString = StringUtils.defaultIfBlank(request.getHeader(X_CLIENT_TOKEN_USER), "{}");
        UserContextHolder.getInstance().setContext(new ObjectMapper().readValue(userInfoString, Map.class));
        return true;
    }
    private void checkToken(String token){
        // TODO
        log.debug("校验token：{}", token);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.getInstance().clear();
    }
}
