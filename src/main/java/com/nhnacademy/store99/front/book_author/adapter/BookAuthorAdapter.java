package com.nhnacademy.store99.front.book_author.adapter;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "BookAuthor-bookstore", url = "${gateway.url}/open/bookstore/v1/books/author", decode404 = true)
public interface BookAuthorAdapter {
}
