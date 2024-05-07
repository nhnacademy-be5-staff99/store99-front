package com.nhnacademy.store99.front.index.controller;

import com.nhnacademy.store99.front.book.Response.BookPageResponse;
import com.nhnacademy.store99.front.book.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainViewController {
    private final BookService bookService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model, Pageable pageable) {
        List<BookPageResponse> list = bookService.getBooks(pageable).getContent();

        model.addAttribute("bestBooksList", list);

        return "index";
    }
}
