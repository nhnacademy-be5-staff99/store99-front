package com.nhnacademy.store99.front.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자 카테고리 관리 컨트롤러
 *
 * @author seunggyu-kim
 */
@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    @GetMapping
    public String viewAdminCategoryManagementPage() {
        return "admin/category/category_management";
    }
}
