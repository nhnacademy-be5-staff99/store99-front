package com.nhnacademy.store99.front.signup.controller;

import static com.nhnacademy.store99.front.signup.service.SignUpService.code;

import com.nhnacademy.store99.front.signup.dto.EmailDto;
import com.nhnacademy.store99.front.signup.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }
    @GetMapping("/sign-up")
    public String SignUpView() {
        return "sign-up";
    }

    @PostMapping("/mailConfirm")
    public ResponseEntity<String> mailConfirm(@RequestBody EmailDto emailDto) {
        try {
            signUpService.mailConfirm(emailDto.getEmail());
            return ResponseEntity.ok(code);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
}
