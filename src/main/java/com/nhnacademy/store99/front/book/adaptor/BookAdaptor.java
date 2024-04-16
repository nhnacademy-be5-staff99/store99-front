package com.nhnacademy.store99.front.book.adaptor;

import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.book_author.response.BookAuthorResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 도서 관리 어댑터
 * <p>IoenFeign을 사용하여 도서 api호출.
 *
 * @author yrrho2
 */
@FeignClient(value = "bookstore-book", url = "${gateway.url}/open/bookstore/v1/books", decode404 = true)
public interface BookAdaptor {
    @GetMapping()
    ResponseEntity<CommonResponse<CustomPageImpl<BookResponse>>> getBooks(@RequestParam(value = "page") int page);

    @GetMapping("/author/book")
    ResponseEntity<CommonResponse<BookAuthorResponse>> getBookAuthor(@RequestParam(value = "bookId") Long bookId);

}
