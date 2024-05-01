package com.nhnacademy.store99.front.book.adapter;

import com.nhnacademy.store99.front.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import java.util.List;
import java.util.Set;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author seunggyu-kim
 */
@FeignClient(value = "book-open-adapter", url = "${gateway.url}/open/bookstore/v1/books")
public interface BookOpenAdapter {
    @GetMapping("/simple")
    CommonResponse<List<SimpleBookResponse>> getSimpleBooks(@RequestParam Set<Long> bookIds);
}
