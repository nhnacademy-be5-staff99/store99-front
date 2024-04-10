package com.nhnacademy.store99.front.category.dto.request;

import lombok.Getter;

@Getter
public class AddCategoryRequest {
    private String categoryName;

    private String parentCategoryId;
}
