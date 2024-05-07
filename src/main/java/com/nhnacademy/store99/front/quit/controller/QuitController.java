//package com.nhnacademy.store99.front.quit.controller;
//
//import com.nhnacademy.store99.front.common.response.CommonListResponse;
//import com.nhnacademy.store99.front.quit.service.QuitService;
//import com.nhnacademy.store99.front.signup.dto.EmailDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//
///**
// * @author jinhyogyeom
// */
//@Controller
//@RequiredArgsConstructor
//public class QuitController {
//
//    @Autowired
//    private final QuitService quitService;
//
//
//    @PostMapping("/quit")
//    public ResponseEntity<CommonListResponse<String >> quit(EmailDto emailDto) {
//        quitService.quit(emailDto.getEmail());
//    }
//}
