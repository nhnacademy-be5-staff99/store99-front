package com.nhnacademy.store99.front.category.controller;

import com.nhnacademy.store99.front.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.front.category.service.CategoryAdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 관리자 카테고리 관리 컨트롤러
 *
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminController {
    private final CategoryAdminService categoryAdminService;

    @GetMapping
    public ModelAndView viewAdminCategoryManagementPage() {
        List<CategoryForAdminResponse> categories = categoryAdminService.getCategories();

        ModelAndView mav = new ModelAndView("admin/category/category_management");
        mav.addObject("categories", categories);
        return mav;
    }

    @PostMapping
    public ModelAndView addCategory() {
        return new ModelAndView("redirect:/admin/categories");
    }

    @PostMapping
    public ModelAndView modifyCategory() {
        return new ModelAndView("redirect:/admin/categories");
    }

    @PostMapping
    public ModelAndView removeCategory() {
        return new ModelAndView("redirect:/admin/categories");
    }
}
