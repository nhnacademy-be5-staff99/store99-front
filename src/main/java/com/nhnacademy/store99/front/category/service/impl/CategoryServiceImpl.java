package com.nhnacademy.store99.front.category.service.impl;

import com.nhnacademy.store99.front.category.adapter.CategoryAdapter;
import com.nhnacademy.store99.front.category.dto.response.CategoryChildrenListAndRouteResponse;
import com.nhnacademy.store99.front.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryAdapter categoryAdapter;

    @Override
    public CategoryChildrenListAndRouteResponse getChildrenListAndRoute(final Long categoryId) {
        CategoryChildrenListAndRouteResponse response =
                categoryAdapter.getCategoryChildrenListAndRoute(categoryId).getResult();
        return response;
    }
}
