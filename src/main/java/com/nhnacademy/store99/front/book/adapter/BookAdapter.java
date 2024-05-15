package com.nhnacademy.store99.front.book.adapter;

import com.nhnacademy.store99.front.common.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 도서 관리 어댑터
 * <p>IoenFeign을 사용하여 도서 api호출.
 *
 * @author yrrho2
 */
@FeignClient(value = "bookAdaptor", url = "${gateway.url}/api/bookstore/admin/v1/books")
public interface BookAdapter {
    @DeleteMapping("/{bookId}")
    CommonResponse<Void> deleteBook(@PathVariable("bookId") Long bookId);

    @PutMapping("/{bookId}/restore")
    CommonResponse<Void> restoreBook(@PathVariable("bookId") Long bookId);
}
