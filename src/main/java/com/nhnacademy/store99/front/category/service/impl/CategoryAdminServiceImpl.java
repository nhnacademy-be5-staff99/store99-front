package com.nhnacademy.store99.front.category.service.impl;

import com.nhnacademy.store99.front.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.RemoveCategoryRequest;
import com.nhnacademy.store99.front.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.front.category.service.CategoryAdminService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 관리자 카테고리 관리 서비스 구현체
 *
 * @author seunggyu-kim
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class CategoryAdminServiceImpl implements CategoryAdminService {
    @Override
    public List<CategoryForAdminResponse> getCategories() {
        List<CategoryForAdminResponse> categories = new ArrayList<>();
        categories.add(new CategoryForAdminResponse(1L, "카테고리", null, null));
        categories.add(new CategoryForAdminResponse(2L, "카테고리", 1L, null));
        categories.add(new CategoryForAdminResponse(3L, "카테고리", 2L, LocalDateTime.now()));
        return categories;
    }

    @Override
    public void addCategory(final AddCategoryRequest category) {
    }

    @Override
    public void modifyCategory(final ModifyCategoryRequest category) {
    }

    @Override
    public void removeCategory(final RemoveCategoryRequest categoryId) {
    }
}