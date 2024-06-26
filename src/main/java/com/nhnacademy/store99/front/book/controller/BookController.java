package com.nhnacademy.store99.front.book.controller;

import com.nhnacademy.store99.front.book.dto.response.BookPageResponse;
import com.nhnacademy.store99.front.book.dto.response.BookResponse;
import com.nhnacademy.store99.front.book.service.BookService;
import com.nhnacademy.store99.front.category.dto.response.CategoryChildrenListAndRouteResponse;
import com.nhnacademy.store99.front.category.service.CategoryService;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 도서 뷰 컨트롤러
 * <p>도서 페이지를 보여주는 컨트롤러
 *
 * @author seunggyu-kim
 */
@Controller
@RequiredArgsConstructor
public class BookController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @GetMapping("/books")
    public String viewBookSalesList(Model model, @PageableDefault(size = 10) Pageable pageable) {
        CategoryChildrenListAndRouteResponse categoryChildrenListAndRoute =
                categoryService.getChildrenListAndRoute(1L);

        model.addAttribute("categoryChildrenListAndRoute", categoryChildrenListAndRoute);
        CustomPageImpl<BookPageResponse> booksDTOPage = bookService.getBooks(pageable);
        model.addAttribute("booksDTOPage", booksDTOPage);
        return "book/book_sales_list";
    }

    @GetMapping("/books/{bookId}")
    public String viewBookSalesPage(@PathVariable Long bookId, Model model) {
        BookResponse bookResponse = bookService.getBook(bookId);
        model.addAttribute("bookData", bookResponse);
        return "book/book_sales_page";
    }

    @GetMapping("/categories/{categoryId}/books")
    public String viewBookSalesListByCategory(@PathVariable Long categoryId, Model model,
                                              @PageableDefault(size = 10) Pageable pageable) {
        CategoryChildrenListAndRouteResponse categoryChildrenListAndRoute =
                categoryService.getChildrenListAndRoute(categoryId);
        model.addAttribute("categoryChildrenListAndRoute", categoryChildrenListAndRoute);

        CustomPageImpl<BookPageResponse> booksDTOPage = bookService.getBooksByCategory(categoryId, pageable);
        model.addAttribute("booksDTOPage", booksDTOPage);
        model.addAttribute("url", "/categories");
        return "book/book_sales_list";
    }
}
