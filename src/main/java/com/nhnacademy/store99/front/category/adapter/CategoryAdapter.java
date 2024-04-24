package com.nhnacademy.store99.front.category.adapter;

import com.nhnacademy.store99.front.category.dto.response.CategoryChildrenListAndRouteResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author seunggyu-kim
 */
@FeignClient(value = "category-adapter", url = "${gateway.url}/open/bookstore/v1/categories")
public interface CategoryAdapter {
    @GetMapping
    CommonResponse<CategoryChildrenListAndRouteResponse> getCategoryChildrenListAndRoute(@RequestParam Long categoryId);
}
