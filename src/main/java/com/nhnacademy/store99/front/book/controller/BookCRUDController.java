package com.nhnacademy.store99.front.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookCRUDController {

    @GetMapping("/book")
    public String getBookData() {
        return "bookdata";
    }
}
