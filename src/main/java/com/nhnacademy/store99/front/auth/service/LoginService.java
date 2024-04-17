package com.nhnacademy.store99.front.auth.service;

import com.nhnacademy.store99.front.auth.adapter.LoginAdapter;
import com.nhnacademy.store99.front.auth.dto.LoginRequest;
import com.nhnacademy.store99.front.auth.dto.LoginResponse;
import com.nhnacademy.store99.front.auth.exception.LoginFailException;
import com.nhnacademy.store99.front.auth.exception.LogoutFailException;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import com.nhnacademy.store99.front.common.util.CookieUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Ahyeon Song
 */
@Service
@Slf4j
public class LoginService {
    private final LoginAdapter loginAdapter;

    public LoginService(LoginAdapter loginAdapter) {
        this.loginAdapter = loginAdapter;
    }

    /**
     * auth server 로 email, password 를 보내 로그인 및 JWT 발급 요청
     * <p>header 에서 X-USER-TOKEN 를 통해 access token 을 전달받는다
     *
     * @param request
     * @return LoginResponse
     */
    public String doLogin(LoginRequest request) {
        ResponseEntity<LoginResponse> loginResponse;

        try {
            loginResponse = loginAdapter.userLogin(request);
        } catch (HttpClientErrorException e) {
            throw new LoginFailException("로그인 실패", e);
        }

        log.debug("token 가져오기 성공");
        return loginResponse.getHeaders().getFirst("X-USER-TOKEN");
    }

    /**
     * 로그아웃 실제 로직
     *
     * <p>auth server 로 요청을 보내 uuid 삭제, security 관련 객체 삭제
     * <p>client 의 X-USER-TOKEN Cookie 삭제
     * <p>thread local 의 X-USER-TOKEN 삭제
     * @param request
     * @param response
     */
    public void doLogout(HttpServletRequest request, HttpServletResponse response) {
        CommonResponse<String> logoutResponse;

        try {
            logoutResponse = loginAdapter.userLogout(XUserTokenThreadLocal.getXUserToken());
        } catch (HttpClientErrorException e) {
            throw new LogoutFailException("로그아웃 실패");
        }
        log.debug("로그아웃 성공 : {}", logoutResponse.getResult());

        CookieUtils.deleteCookie(request, response, "X-USER-TOKEN");
        log.debug("X-USER-TOKEN Cookie 삭제 성공");

        XUserTokenThreadLocal.reset();
        log.debug("ThreadLocal 에서 X-USER-TOKEN 삭제 성공");
    }
}
