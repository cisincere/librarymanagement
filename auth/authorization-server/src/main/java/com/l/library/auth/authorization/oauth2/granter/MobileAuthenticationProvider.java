package com.l.library.auth.authorization.oauth2.granter;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 手机验证码登入
 */
public class MobileAuthenticationProvider extends DaoAuthenticationProvider {

    public MobileAuthenticationProvider(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }
}