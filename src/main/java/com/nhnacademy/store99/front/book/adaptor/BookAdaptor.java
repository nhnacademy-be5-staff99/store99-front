package com.nhnacademy.store99.front.book.adaptor;

import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.common.response.CommonListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 도서 관리 어댑터
 * <p>IoenFeign을 사용하여 도서 api호출.
 *
 * @author yrrho2
 */
@FeignClient(value = "bookstore-book", url = "localhost:8760/open/bookstore/v1/books", decode404 = true)
public interface BookAdaptor {
    @GetMapping()
    ResponseEntity<CommonListResponse<BookResponse>> getBooks();
}
