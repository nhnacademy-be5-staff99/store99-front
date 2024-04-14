package com.nhnacademy.store99.front.category.adapter;

import com.nhnacademy.store99.front.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * /api/bookstore/admin/v1/categories과 연결하는 FeignClient
 *
 * @author seunggyu-kim
 */
@FeignClient(value = "category-admin-adapter", url = "${gateway.url}/api/bookstore/admin/v1/categories")
public interface CategoryAdminAdapter {
    @GetMapping
    CommonResponse<Void> addCategory(@RequestHeader("X-USER-TOKEN") String xUserToken, AddCategoryRequest request);
}
