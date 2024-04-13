package com.nhnacademy.store99.front.category.controller;

import com.nhnacademy.store99.front.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.RemoveCategoryRequest;
import com.nhnacademy.store99.front.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.front.category.service.CategoryAdminService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ModelAndView viewAdminCategoryManagementPage(Pageable pageable) {
        Page<CategoryForAdminResponse> categories = categoryAdminService.getCategories(pageable);

        ModelAndView mav = new ModelAndView("admin/category/category_management");
        mav.addObject("categories", categories);
        return mav;
    }

    @PostMapping
    public ModelAndView addCategory(@ModelAttribute @Valid AddCategoryRequest request) {
        categoryAdminService.addCategory(request);
        return new ModelAndView("redirect:/admin/categories");
    }

    @PutMapping
    public ModelAndView modifyCategory(@ModelAttribute @Valid ModifyCategoryRequest request) {
        return new ModelAndView("redirect:/admin/categories");
    }

    @DeleteMapping
    public ModelAndView removeCategory(@ModelAttribute @Valid RemoveCategoryRequest request) {
        return new ModelAndView("redirect:/admin/categories");
    }
}
