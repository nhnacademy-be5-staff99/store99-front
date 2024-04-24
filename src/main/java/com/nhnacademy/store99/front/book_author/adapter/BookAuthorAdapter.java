package com.nhnacademy.store99.front.book_author.adapter;

import com.nhnacademy.store99.front.book_author.response.BookAuthorResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "BookAuthor-bookstore", url = "${gateway.url}/open/bookstore/v1/books/author", decode404 = true)
public interface BookAuthorAdapter {
    @GetMapping("/book")
    ResponseEntity<CommonResponse<BookAuthorResponse>> getBookAuthor(@RequestParam(value = "bookId") Long bookId);

}
