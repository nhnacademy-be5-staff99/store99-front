package com.nhnacademy.store99.front.auth.cookie;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevCookieSecurityConfig implements CookieSecurityConfig {
    @Override
    public boolean isSecure() {
        return false;
    }
}
