package com.nhnacademy.store99.front.category.dto.response;

import com.nhnacademy.store99.front.category.dto.ActiveCategoryIdAndNameDto;
import java.util.List;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class CategoryChildrenListAndRouteResponse {
    /**
     * 현재 검색된 카테고리까지의 경로를 역순으로 반환합니다.
     */
    public List<ActiveCategoryIdAndNameDto> nowCategoryRoute;

    /**
     * 현재 카테고리의 자식 카테고리 목록을 반환합니다.
     */
    public List<ActiveCategoryIdAndNameDto> childrenCategoryList;


}