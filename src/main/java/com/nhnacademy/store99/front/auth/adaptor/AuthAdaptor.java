package com.nhnacademy.store99.front.auth.adaptor;

import com.nhnacademy.store99.front.auth.dto.response.AdminCheckResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "store99-gateway-service", url = "${gateway.url}/api/bookstore/v1/admin", decode404 = true)
public interface AuthAdaptor {
    @GetMapping("/check")
    ResponseEntity<CommonResponse<AdminCheckResponse>> checkAdmin(@RequestHeader("X-USER-TOKEN") String xUserToken);
}
