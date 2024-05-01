package com.nhnacademy.store99.front.config;

import com.nhnacademy.store99.front.auth.service.AdminCheckService;
import com.nhnacademy.store99.front.common.interceptor.LoginStatusCheckInterceptor;
import com.nhnacademy.store99.front.common.interceptor.XUserTokenCheckForAdminInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer 설정
 *
 * @author seunggyu-kim
 * @author Ahyeon Song
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ObjectProvider<AdminCheckService> adminCheckServices;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(loginStatusCheckInterceptor())
                .excludePathPatterns("/error", "/static/**", "/assets/**", "/favicon.ico")
                .order(1);
        registry.addInterceptor(new XUserTokenCheckForAdminInterceptor()).addPathPatterns("/admin/**").order(2);
    }

    @Bean
    public LoginStatusCheckInterceptor loginStatusCheckInterceptor() {
        return new LoginStatusCheckInterceptor(adminCheckServices);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
