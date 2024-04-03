package com.nhnacademy.store99.front.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping
    public String getAdminIndex() {
        return "admin/admin_index";
    }
}
