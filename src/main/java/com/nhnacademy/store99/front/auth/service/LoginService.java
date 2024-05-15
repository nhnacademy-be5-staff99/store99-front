package com.nhnacademy.store99.front.auth.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ahyeon Song
 */
@Service
@Slf4j
public class LoginService {
    private final LoginAdapter loginAdapter;
    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gateway;
    public static boolean isDeleted;

    public LoginService(LoginAdapter loginAdapter, RestTemplate restTemplate) {
        this.loginAdapter = loginAdapter;
        this.restTemplate = restTemplate;
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

        String resultMessage = loginResponse.getBody().getHeader().getResultMessage();

        log.debug("token 가져오기 성공");
        return loginResponse.getHeaders().getFirst("X-USER-TOKEN");
    }

    /**
     * 로그아웃 실제 로직
     *
     * <p>auth server 로 요청을 보내 uuid 삭제, security 관련 객체 삭제
     * <p>client 의 X-USER-TOKEN Cookie 삭제
     * <p>thread local 의 X-USER-TOKEN 삭제
     *
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

    public void deletedCheck(LoginRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"email\":\"" + request.getEmail() + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);


        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                gateway + "/open/bookstore/v1/user/deletedCheck",
                requestEntity,
                String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("삭제 확인 요청이 성공적으로 전송되었습니다.");
            String response = responseEntity.getBody();
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response);
                Boolean result = jsonNode.get("result").asBoolean();
                isDeleted = result;
            } catch (Exception e) {
                System.out.println("응답 데이터 처리 중 오류 발생: " + e.getMessage());
            }
        } else {
            System.out.println("삭제 확인 요청을 보내는 중 오류가 발생하였습니다. 응답 코드: " + responseEntity.getStatusCodeValue());
        }
    }
}
