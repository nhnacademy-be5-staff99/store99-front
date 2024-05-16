package com.nhnacademy.store99.front.signup.controller;

import static com.nhnacademy.store99.front.signup.service.SignUpService.isDuplicate;

import com.nhnacademy.store99.front.common.response.CommonHeader;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.signup.dto.EmailDto;
import com.nhnacademy.store99.front.signup.dto.PasswordDto;
import com.nhnacademy.store99.front.signup.dto.SignUpDto;
import com.nhnacademy.store99.front.signup.service.SignUpService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author jinhyogyeom
 */
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

    /**
     * 이메일 서비스를 요청하는 메소드
     * 이메일 인증번호를 반환
     *
     * @param emailDto
     * @return String
     */
    @PostMapping("/mailConfirm")
    public ResponseEntity<String> mailConfirm(@RequestBody EmailDto emailDto, HttpSession httpSession) {
        try {
            String code = signUpService.mailConfirm(emailDto.getEmail());
            httpSession.setAttribute("code", code);
            return ResponseEntity.ok(code);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    /**
     * 중복검사 서비스를 요청하는 메소드
     * 중복검사 결과 반환
     *
     * @param passwordDto
     * @return String
     */
    @PostMapping("/duplicateCheck")
    public ResponseEntity<String> duplicateCheck(@RequestBody PasswordDto passwordDto) {
        try {
            signUpService.duplicateCheck(passwordDto.getPassword());
            return ResponseEntity.ok(isDuplicate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    /**
     * 회원가입 서비스를 요청하는 메소드
     * 회원가입 성공 메세지 반환
     *
     * @param signUpDto
     * @return String
     */
    @PostMapping("/sign-up")
    public ResponseEntity<CommonResponse<String>> signUp(@RequestBody SignUpDto signUpDto) {
        try {
            signUpService.signUp(signUpDto);
            CommonHeader header = CommonHeader.builder()
                    .httpStatus(HttpStatus.OK)
                    .resultMessage("Success")
                    .build();
            CommonResponse<String> response = CommonResponse.<String>builder()
                    .header(header)
                    .result("User registration successful.")
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            CommonHeader header = CommonHeader.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .resultMessage("Error occurred: " + e.getMessage())
                    .build();
            CommonResponse<String> response = CommonResponse.<String>builder()
                    .header(header)
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
