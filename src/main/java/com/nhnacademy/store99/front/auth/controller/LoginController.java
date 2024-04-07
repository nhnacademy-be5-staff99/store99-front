package com.nhnacademy.store99.front.auth.controller;

import com.nhnacademy.store99.front.auth.dto.LoginRequest;
import com.nhnacademy.store99.front.auth.service.LoginService;
import com.nhnacademy.store99.front.common.response.CommonHeader;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // 로그인 페이지 진입
    @GetMapping("/login_form")
    public ModelAndView getLoginForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login_form");

        return mv;
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<String>> doLogin(@RequestBody LoginRequest request) {
        String accessToken = loginService.doLogin(request);
        accessToken = accessToken.replace(" ", "");

        CommonHeader header = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .resultMessage("Login success")
                .build();

        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .header(header)
                .result("로그인 성공")
                .build();
        ResponseCookie cookie = ResponseCookie.from("X-USER-TOKEN", accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .build();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString());

        return new ResponseEntity<>(commonResponse, responseHeaders, HttpStatus.OK);
    }

}
