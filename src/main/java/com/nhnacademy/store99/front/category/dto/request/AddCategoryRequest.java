package com.nhnacademy.store99.front.category.dto.request;

import lombok.Getter;

/**
 * 카테고리 추가 요청 DTO
 *
 * @author seunggyu-kim
 */
@Getter
public class AddCategoryRequest {
    private String categoryName;

    private String parentCategoryId;
}
