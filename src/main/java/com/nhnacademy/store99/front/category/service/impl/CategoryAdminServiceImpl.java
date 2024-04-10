package com.nhnacademy.store99.front.category.service.impl;

import com.nhnacademy.store99.front.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.RemoveCategoryRequest;
import com.nhnacademy.store99.front.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.front.category.service.CategoryAdminService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CategoryAdminServiceImpl implements CategoryAdminService {
    @Override
    public List<CategoryForAdminResponse> getCategories() {
        return List.of();
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
