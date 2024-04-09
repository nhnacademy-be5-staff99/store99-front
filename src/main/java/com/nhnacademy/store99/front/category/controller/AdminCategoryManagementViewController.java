package com.nhnacademy.store99.front.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryManagementViewController {
    @GetMapping
    public String getAdminCategoryManagement() {
        return "admin/category/category_management";
    }
}
