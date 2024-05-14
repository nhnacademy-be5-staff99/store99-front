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
        List<IndexBookResponse> science = bookService.getBooksByCategory(2L);
        List<IndexBookResponse> computer = bookService.getBooksByCategory(3L);
        List<IndexBookResponse> economy = bookService.getBooksByCategory(4L);
        model.addAttribute("bestBooks", bestBooks);
        model.addAttribute("latestBooks", latestBooks);
        model.addAttribute("science", science);
        model.addAttribute("computer", computer);
        model.addAttribute("economy", economy);


        return "index";
    }
}
