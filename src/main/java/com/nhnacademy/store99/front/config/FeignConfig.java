package com.nhnacademy.store99.front.config;

import com.nhnacademy.store99.front.common.decoder.CommonResponseDecoder;
import feign.codec.Decoder;
import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import feign.Logger;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author seunggyu-kim
 * @author Ahyeon Song
 */
@Configuration
@Slf4j
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
            log.debug("url : {}", url);
            if (url.startsWith(gatewayUrl + "/api")) {
                String xUserToken = getXUserToken();
                template.header("X-USER-TOKEN", xUserToken);
            }
        };
    }

    private String getXUserToken() {
        return XUserTokenThreadLocal.getXUserToken();
    }

    /**
     *
     * @return
     */
//    @Bean
//    public Decoder decoder() {
//        return new Decoder() {
//            private final ObjectMapper objectMapper = new ObjectMapper();
//
//            @Override
//            public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
//
//                String body = Util.toString(response.body().asReader());
//                JavaType javaType = objectMapper.getTypeFactory()
//                        .constructParametricType(CommonResponse.class, TypeFactory.rawClass(type));
//
//                CommonResponse<?> commonResponse = objectMapper.readValue(body, javaType);
//                return commonResponse.getResult();
//            }
//        };
//    }

    /**
     * Feign Client 의 custom logger
     * FULL : header, body, metadata, request, response 를 표시
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Decoder feignDecoder(final Decoder decoder) {
        return new CommonResponseDecoder(decoder);
    }
}
