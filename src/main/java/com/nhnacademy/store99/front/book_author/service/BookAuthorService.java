package com.nhnacademy.store99.front.book_author.service;

import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.book_author.adaptor.BookAuthorAdaptor;
import com.nhnacademy.store99.front.book_author.response.BookAuthorResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookAuthorService {
    private final BookAuthorAdaptor bookAuthorAdaptor;

    public BookAuthorService(BookAuthorAdaptor bookAuthorAdaptor) {
        this.bookAuthorAdaptor = bookAuthorAdaptor;
    }

    public List<BookAuthorResponse> bookAuthorList(List<BookResponse> bookResponsesList) {
        List<BookAuthorResponse> bookAuthorResponseList = new ArrayList<>();

        for (BookResponse bookResponse : bookResponsesList) {
            Long bookId = bookResponse.getId();
            BookAuthorResponse bookAuthorResponse;
            try {
                bookAuthorResponse = bookAuthorAdaptor.getBookAuthor(bookId).getBody().getResult();
            } catch (Exception e) {
                bookAuthorResponse = null;
            }

            bookAuthorResponseList.add(bookAuthorResponse);
        }
        return bookAuthorResponseList;
    }
}
