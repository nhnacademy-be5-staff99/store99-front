package com.nhnacademy.store99.front.category.adapter;

import com.nhnacademy.store99.front.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.front.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * /api/bookstore/admin/v1/categories과 연결하는 FeignClient
 *
 * @author seunggyu-kim
 */
@FeignClient(value = "category-admin-adapter", url = "${gateway.url}/api/bookstore/admin/v1/categories")
public interface CategoryAdminAdapter {
    @GetMapping
    CommonResponse<CustomPageImpl<CategoryForAdminResponse>> getCategories(Pageable pageable);

    @PostMapping
    CommonResponse<Void> addCategory(AddCategoryRequest request);

    @PutMapping("/{categoryId}")
    CommonResponse<Void> modifyCategory(@PathVariable Long categoryId, ModifyCategoryRequest request);

    @DeleteMapping("/{categoryId}")
    CommonResponse<Void> removeCategory(@PathVariable Long categoryId);

    @PutMapping("/{categoryId}/restore")
    CommonResponse<Void> restoreCategory(@PathVariable Long categoryId);
}
