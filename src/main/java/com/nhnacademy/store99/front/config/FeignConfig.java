package com.nhnacademy.store99.front.config;

import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import com.nhnacademy.store99.front.config.feign_decoder.FeignClientErrorDecoder;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author seunggyu-kim
 * @author Ahyeon Song
 */
@Slf4j
@Configuration
@EnableFeignClients(basePackages = "**.adapter")
public class FeignConfig {

    @Value("${gateway.url}")
    private String gatewayUrl;

    /**
     * feign client 공통 헤더 추가
     * feign client 동작 시에 thread local 에서 쿠키를 가져와 header 에 담는 메소드
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        log.debug("request interceptor 동작");
        return template -> {
            String url = template.feignTarget().url();
            if (url.startsWith(gatewayUrl + "/api")) {
                String xUserToken = getXUserToken();
                template.header("X-USER-TOKEN", xUserToken);
            }
        };
    }

    @Bean
    public FeignClientErrorDecoder feignClientErrorDecoder() {
        return new FeignClientErrorDecoder();
    }

    private String getXUserToken() {
        return XUserTokenThreadLocal.getXUserToken();
    }
}
