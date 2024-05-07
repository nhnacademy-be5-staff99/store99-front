package com.nhnacademy.store99.front.book.adapter;

import com.nhnacademy.store99.front.book.Response.BookPageResponse;
import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.book_author.response.BookAuthorResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 도서 관리 어댑터
 * <p>IoenFeign을 사용하여 도서 api호출.
 *
 * @author yrrho2
 */
@FeignClient(value = "bookAdaptor", url = "${gateway.url}/open/bookstore/v1/books")
public interface BookAdapter {
    @GetMapping("/")
    CommonResponse<CustomPageImpl<BookPageResponse>> getBooks(
            @RequestParam(value = "page") Pageable pageable);

    @GetMapping("/author/book")
    ResponseEntity<CommonResponse<BookAuthorResponse>> getBookAuthor(@RequestParam(value = "bookId") Long bookId);

    @GetMapping("/{bookId}")
    CommonResponse<BookResponse> getBook(@PathVariable(value = "bookId") Long bookId);

    // 자식 카테고리까지 포함된 도서 목록
    @GetMapping("/categories/{categoryId}")
    CommonResponse<CustomPageImpl<BookPageResponse>> getBooksByCategory(@PathVariable("categoryId") Long categoryId,
                                                                        Pageable pageable);

}
