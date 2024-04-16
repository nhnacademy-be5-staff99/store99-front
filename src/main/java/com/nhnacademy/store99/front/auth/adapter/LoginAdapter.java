package com.nhnacademy.store99.front.auth.adapter;

import com.nhnacademy.store99.front.auth.dto.LoginRequest;
import com.nhnacademy.store99.front.auth.dto.LoginResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * OpenFeign 을 통해 auth server 로 로그인 요청
 *
 * @author Aheyeon Song
 */
@FeignClient(name = "LoginOpenFeign", url = "${gateway.url}/auth")
public interface LoginAdapter {

    @PostMapping(value = "/v1/auth/login")
    ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest request);

    @PostMapping(value = "/v1/auth/logout")
    CommonResponse<String> userLogout();
}
