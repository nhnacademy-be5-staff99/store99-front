package com.nhnacademy.store99.front.auth.adapter;

import com.nhnacademy.store99.front.auth.dto.LoginRequest;
import com.nhnacademy.store99.front.auth.dto.LoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "LoginOpenFeign", url = "${gateway.url}/auth")
public interface LoginOpenFeign {

    @PostMapping(value = "/v1/auth/login")
    ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest request);
}
