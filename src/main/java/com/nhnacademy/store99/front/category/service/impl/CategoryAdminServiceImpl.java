package com.nhnacademy.store99.front.category.service.impl;

import com.nhnacademy.store99.front.category.adapter.CategoryAdminAdapter;
import com.nhnacademy.store99.front.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.front.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.front.category.service.CategoryAdminService;
import com.nhnacademy.store99.front.common.aop.AdminPermissionCheck;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 관리자 카테고리 관리 서비스 구현체
 *
 * @author seunggyu-kim
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CategoryAdminServiceImpl implements CategoryAdminService {
    private final CategoryAdminAdapter categoryAdminAdapter;

    @Override
    @AdminPermissionCheck
    public Page<CategoryForAdminResponse> getCategories(Pageable pageable) {
        return categoryAdminAdapter.getCategories(pageable).getResult();
    }

    @Override
    @AdminPermissionCheck
    public void addCategory(final AddCategoryRequest request) {
        CommonResponse<Void> response =
                categoryAdminAdapter.addCategory(request);
    }

    @Override
    @AdminPermissionCheck
    public void modifyCategory(final Long categoryId, final ModifyCategoryRequest request) {
        categoryAdminAdapter.modifyCategory(categoryId, request);
    }

    @Override
    public void removeCategory(final Long categoryId) {
        categoryAdminAdapter.removeCategory(categoryId);
    }

    @Override
    @AdminPermissionCheck
    public void restoreCategory(final Long categoryId) {
        categoryAdminAdapter.restoreCategory(categoryId);
    }
}
