package com.nhnacademy.store99.front.auth.adaptor;

import com.nhnacademy.store99.front.auth.dto.response.AdminCheckResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 관리자 권한 체크 어댑터
 * <p>OpenFeign을 사용하여 관리자 권한 체크 API를 호출한다. API 호출 시 X-USER-TOKEN 헤더를 전달한다.
 *
 * @author seunggyu-kim
 */
@FeignClient(value = "store99-gateway-service", url = "${gateway.url}/api/bookstore/v1/admin", decode404 = true)
public interface AuthAdaptor {
    @GetMapping("/check")
    ResponseEntity<CommonResponse<AdminCheckResponse>> checkAdmin(@RequestHeader("X-USER-TOKEN") String xUserToken);
}
