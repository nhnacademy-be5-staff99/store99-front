package com.nhnacademy.store99.front.like.dto.response;

import com.nhnacademy.store99.front.common.response.CommonHeader;
import lombok.Getter;

@Getter
public class LikeResponse {

    private final CommonHeader header;

    private final String result;

    public LikeResponse(CommonHeader header, String result) {
        this.header = header;
        this.result = result;
    }
}
