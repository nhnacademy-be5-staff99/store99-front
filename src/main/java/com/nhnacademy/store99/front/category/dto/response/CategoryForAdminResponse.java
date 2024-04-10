package com.nhnacademy.store99.front.category.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 카테고리 조회 응답 DTO
 *
 * @author seunggyu-kim
 */
@AllArgsConstructor
@Getter
public class CategoryForAdminResponse {
    private Long id;

    private String categoryName;

    private Long parentCategoryId;

    private LocalDateTime deletedAt;
}
