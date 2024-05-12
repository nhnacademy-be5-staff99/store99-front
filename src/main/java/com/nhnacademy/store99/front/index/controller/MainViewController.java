package com.nhnacademy.store99.front.index.controller;

import com.nhnacademy.store99.front.book.service.BookService;
import com.nhnacademy.store99.front.index.dto.response.IndexBookResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainViewController {
    private final BookService bookService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        List<IndexBookResponse> bestBooks = bookService.getBestBooks();
        List<IndexBookResponse> latestBooks = bookService.getLatestBooks();
        List science = bookService.getBooksByCategory(2L);
        model.addAttribute("bestBooks", bestBooks);
        model.addAttribute("latestBooks", latestBooks);


        return "index";
    }
}
