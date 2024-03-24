package com.nhnacademy.store99.front.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null ){
            ip = request.getRemoteAddr();
        }
        model.addAttribute("ip",ip);
        return "index";
    }
}
