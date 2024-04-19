package com.nhnacademy.store99.front.category.service.impl;

import com.nhnacademy.store99.front.admin.exception.AdminPermissionDeniedException;
import com.nhnacademy.store99.front.category.adapter.CategoryAdminAdapter;
import com.nhnacademy.store99.front.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.front.category.dto.request.RemoveCategoryRequest;
import com.nhnacademy.store99.front.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.front.category.service.CategoryAdminService;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import feign.FeignException;
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
    public Page<CategoryForAdminResponse> getCategories(Pageable pageable) {
        try {
            return categoryAdminAdapter.getCategories(pageable).getResult();
        } catch (FeignException.Forbidden ex) {
            throw new AdminPermissionDeniedException();
        } catch (FeignException ex) {
            log.error("addCategory error", ex);
            return Page.empty();
        }
    }

    @Override
    public void addCategory(final AddCategoryRequest request) {
        try {
            CommonResponse<Void> response =
                    categoryAdminAdapter.addCategory(request);
            log.debug("categoryAdminAdapter.addCategory response: {}", response);
        } catch (FeignException.Forbidden ex) {
            throw new AdminPermissionDeniedException();
        } catch (FeignException ex) {
            log.error("addCategory error", ex);
        }
    }

    @Override
    public void modifyCategory(final ModifyCategoryRequest request) {
    }

    @Override
    public void removeCategory(final RemoveCategoryRequest request) {
    }
}
