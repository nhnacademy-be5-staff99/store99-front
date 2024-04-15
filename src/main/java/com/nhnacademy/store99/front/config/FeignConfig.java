package com.nhnacademy.store99.front.config;

import com.nhnacademy.store99.front.common.decoder.CommonResponseDecoder;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Decoder feignDecoder(final Decoder decoder) {
        return new CommonResponseDecoder(decoder);
    }
}
