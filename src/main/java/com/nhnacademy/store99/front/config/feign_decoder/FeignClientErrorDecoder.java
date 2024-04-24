package com.nhnacademy.store99.front.config.feign_decoder;

import com.nhnacademy.store99.front.common.exception.DefaultFeignClientError;
import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @author seunggyu-kim
 * @author Ahyeon Song
 */
public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 500:
                return new DefaultFeignClientError("예기치 못한 Feign Client 오류", response.status());
            case 503:
                return new DefaultFeignClientError("백엔드 서버 로딩 오류", response.status());
            default:
                return new ErrorDecoder.Default().decode(methodKey, response);
        }
    }

}
