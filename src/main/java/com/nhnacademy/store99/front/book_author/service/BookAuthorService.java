package com.nhnacademy.store99.front.book_author.service;

import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.book_author.adaptor.BookAuthorAdaptor;
import com.nhnacademy.store99.front.book_author.response.BookAuthorResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class BookAuthorService {
    private final BookAuthorAdaptor bookAuthorAdaptor;

    public BookAuthorService(BookAuthorAdaptor bookAuthorAdaptor) {
        this.bookAuthorAdaptor = bookAuthorAdaptor;
    }

    public Map<Long, String> bookAuthorMap(List<BookResponse> bookResponsesList) {
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

        bookAuthorResponseList.removeIf(Objects::isNull);

        Map<Long, String> bookAuthorMap = new HashMap<>();
        String AuthorName = "";
        for (BookAuthorResponse bookAuthorResponse : bookAuthorResponseList) {
            try {
                AuthorName += bookAuthorResponse.getAuthorName();
            } catch (Exception e) {
                AuthorName += "";
            }
            bookAuthorMap.put(bookAuthorResponse.getBookId(), AuthorName);
            AuthorName = "";
        }
        return bookAuthorMap;
    }
}
