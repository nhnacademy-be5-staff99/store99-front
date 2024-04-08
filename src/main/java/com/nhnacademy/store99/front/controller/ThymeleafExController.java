package com.nhnacademy.store99.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafExController {

    @GetMapping("/ex")
    public String thymeleafExample() {
        return "ThmeleafEx";
    }
}
