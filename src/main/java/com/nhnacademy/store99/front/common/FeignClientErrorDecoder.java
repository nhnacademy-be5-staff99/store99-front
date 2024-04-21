package com.nhnacademy.store99.front.common;

import com.nhnacademy.store99.front.common.exception.DefaultFeignClientError;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 403:
                return new ErrorDecoder.Default().decode(methodKey, response);
            default:
                return new DefaultFeignClientError("예기치 못한 Feign Client 오류");
        }
    }

}
