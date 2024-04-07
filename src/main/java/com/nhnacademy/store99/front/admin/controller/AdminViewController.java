package com.nhnacademy.store99.front.admin.controller;


import com.nhnacademy.store99.front.auth.service.AdminCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminViewController {
    private final AdminCheckService adminCheckService;

    @GetMapping("/admin")
    public String getAdminIndex(@CookieValue("X-USER-TOKEN") String xUserToken) {
        if (adminCheckService.checkAdmin(xUserToken)) {
            return "admin/admin_index";
        }
        return "error/admin_forbidden";
    }
}