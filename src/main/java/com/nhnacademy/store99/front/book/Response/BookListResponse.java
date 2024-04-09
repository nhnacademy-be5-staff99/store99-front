package com.nhnacademy.store99.front.book.Response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookListResponse {
    private List<BookResponse> resultList;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class data {
        private Long id;
        private String bookTitle;
    }

}
