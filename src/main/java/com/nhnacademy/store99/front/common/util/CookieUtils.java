package com.nhnacademy.store99.front.common.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CookieUtils {

    private CookieUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Cookie getCookie(HttpServletRequest req, String name) {
        return Optional.ofNullable(req.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(c -> c.getName().equals(name))
                        .findFirst())
                .orElse(null);
    }

    public static void deleteCookie(HttpServletRequest req, HttpServletResponse res, String name) {
        Cookie cookie = getCookie(req, name);
        Objects.requireNonNull(cookie).setMaxAge(0);
        cookie.setValue("");
        cookie.setPath("/");
        res.addCookie(cookie);
    }
}
