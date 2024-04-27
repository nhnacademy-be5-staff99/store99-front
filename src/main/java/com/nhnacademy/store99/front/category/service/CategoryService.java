package com.nhnacademy.store99.front.category.service;

import com.nhnacademy.store99.front.category.dto.response.CategoryChildrenListAndRouteResponse;

public interface CategoryService {
    CategoryChildrenListAndRouteResponse getChildrenListAndRoute(final Long categoryId);
}
