package com.nhnacademy.store99.front.auth.cookie;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class ProdCookieSecurityConfig implements CookieSecurityConfig {
    @Override
    public boolean isSecure() {
        return true;
    }
}
