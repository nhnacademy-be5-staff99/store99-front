package com.nhnacademy.store99.front.config;

import com.nhnacademy.store99.front.common.interceptor.XUserTokenCheckForAdminInterceptor;
import com.nhnacademy.store99.front.common.interceptor.XUserTokenCheckForUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer 설정
 *
 * @author seunggyu-kim
 * @author Ahyeon Song
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new XUserTokenCheckForAdminInterceptor()).addPathPatterns("/admin/**");
        registry.addInterceptor(new XUserTokenCheckForUserInterceptor())
                .addPathPatterns("/mypage/**, /logout");
    }
}
