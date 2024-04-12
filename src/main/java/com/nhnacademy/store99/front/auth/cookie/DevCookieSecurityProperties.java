package com.nhnacademy.store99.front.auth.cookie;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!prod")
@Component
public class DevCookieSecurityProperties implements CookieSecurityProperties {
    @Override
    public boolean isSecure() {
        return false;
    }
}
