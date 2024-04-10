package com.nhnacademy.store99.front.category.service;

import com.nhnacademy.store99.front.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.RemoveCategoryRequest;
import com.nhnacademy.store99.front.category.dto.response.CategoryForAdminResponse;
import java.util.List;

public interface CategoryAdminService {
    List<CategoryForAdminResponse> getCategories();

    void addCategory(AddCategoryRequest category);

    void modifyCategory(ModifyCategoryRequest category);

    void removeCategory(RemoveCategoryRequest categoryId);
}
