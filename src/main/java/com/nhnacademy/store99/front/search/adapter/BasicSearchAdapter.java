package com.nhnacademy.store99.front.search.adapter;

import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.search.dto.BasicSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "BasicSearchAdapter", url = "${gateway.url}/open/bookstore/v1/search")
public interface BasicSearchAdapter {

    /**
     * 도서 제목이나 저자에 content 를 포함한 도서 리스트를 가져오는 api 호출
     *
     * @param content
     * @return BasicSearchResponse
     */
    @GetMapping
    CommonResponse<BasicSearchResponse> searchBooksByContent(
            @RequestParam(value = "content", defaultValue = "") String content);

}
