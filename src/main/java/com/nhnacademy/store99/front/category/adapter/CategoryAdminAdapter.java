package com.nhnacademy.store99.front.category.adapter;

import com.nhnacademy.store99.front.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.front.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
}
