package com.nhnacademy.store99.front.config;

import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ahyeon Song
 */
@Configuration
public class FeignConfig {

    @Value("${gateway.url}")
    private String gatewayUrl;

    /**
     * feign client 동작 시에 thread local 에서 쿠키를 가져와 header 에 담는 메소드
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            String url = template.url();
            if (url.startsWith(gatewayUrl + "/api/bookstore") || url.startsWith(gatewayUrl + "/api/coupon")) {
                String xUserToken = getXUserToken();
                template.header("X-USER-TOKEN", xUserToken);
            }
        };
    }

    private String getXUserToken() {
        return XUserTokenThreadLocal.getXUserToken();
    }
}
