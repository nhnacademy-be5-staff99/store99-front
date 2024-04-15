package com.nhnacademy.store99.front.category.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private Long id;

    @NotNull
    private String categoryName;

    @Max(10)
    @NotNull
    private Integer categoryDepth;

    private Long parentCategoryId;
}
