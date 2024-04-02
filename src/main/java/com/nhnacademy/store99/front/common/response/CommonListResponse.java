package com.nhnacademy.store99.front.common.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonListResponse<T> {
    private final CommonHeader header;
    private final List<T> resultList;
    private final int totalCount;

    @Builder
    private CommonListResponse(CommonHeader header, List<T> resultList, int totalCount) {
        this.header = header;
        this.resultList = resultList;
        this.totalCount = totalCount;
    }
}