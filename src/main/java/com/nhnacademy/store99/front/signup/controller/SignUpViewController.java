package com.nhnacademy.store99.front.signup.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SignUpViewController {
    @GetMapping("/sign-up")
    public String SignUpView() {
        return "sign-up";
    }

}
