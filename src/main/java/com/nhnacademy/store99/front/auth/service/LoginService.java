package com.nhnacademy.store99.front.auth.service;

import com.nhnacademy.store99.front.auth.adapter.LoginOpenFeign;
import com.nhnacademy.store99.front.auth.dto.LoginRequest;
import com.nhnacademy.store99.front.auth.dto.LoginResponse;
import com.nhnacademy.store99.front.auth.exception.LoginFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Slf4j
public class LoginService {
    private final LoginOpenFeign loginOpenFeign;

    public LoginService(LoginOpenFeign loginOpenFeign) {
        this.loginOpenFeign = loginOpenFeign;
    }

    /**
     * auth server 로 email, password 를 보내 로그인 및 JWT 발급 요청
     * <p>
     * header 에서 X-USER-TOKEN 를 통해 access token 을 전달받는다
     *
     * @param request
     * @return LoginResponse
     */
    public String doLogin(LoginRequest request) {
        ResponseEntity<LoginResponse> loginResponse;

        try {
            loginResponse = loginOpenFeign.userLogin(request);
        } catch (HttpClientErrorException e) {
            throw new LoginFailException("로그인 실패", e);
        }

        log.debug("token 가져오기 성공");
        return loginResponse.getHeaders().getFirst("X-USER-TOKEN");
    }
}
