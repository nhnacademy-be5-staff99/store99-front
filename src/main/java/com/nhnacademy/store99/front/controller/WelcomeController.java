package com.nhnacademy.store99.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController {
    public String index(HttpServletRequest request, Model model){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null ){
            ip = request.getRemoteAddr();
        }
        model.addAttribute("ip",ip);
        return "index";
    }
}
