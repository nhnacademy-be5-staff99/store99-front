package com.nhnacademy.store99.front.book.controller;

import com.nhnacademy.store99.front.book.dto.request.BookRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BookAdminController {
    @GetMapping("/admin/books")
    public String viewManageBookDetail() {
        return "admin/book/book_admin";
    }


    @PostMapping(value = "/admin/books")
    public String postBook(@RequestBody BookRequest bookRequest) {
        System.out.println(bookRequest);
        return "index";
    }
}
