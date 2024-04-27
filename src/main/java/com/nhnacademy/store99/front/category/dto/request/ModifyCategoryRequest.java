package com.nhnacademy.store99.front.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 카테고리 수정 요청 DTO
 *
 * @author seunggyu-kim
 */
@Getter
@AllArgsConstructor
public class ModifyCategoryRequest {
    private String categoryName;

    private Long parentCategoryId;
}
