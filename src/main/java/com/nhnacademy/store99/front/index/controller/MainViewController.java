package com.nhnacademy.store99.front.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {

    @GetMapping({"/", "/index"})
    public String getIndex() {
        return "index";
    }
}
