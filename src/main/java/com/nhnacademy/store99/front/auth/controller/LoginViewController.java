package com.nhnacademy.store99.front.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    // 로그인 페이지 진입
    @GetMapping("/login_form")
    public String getLoginForm() {
        return "login_form";
    }

}
