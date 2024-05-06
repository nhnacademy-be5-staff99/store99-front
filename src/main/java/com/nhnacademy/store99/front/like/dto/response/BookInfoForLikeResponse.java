package com.nhnacademy.store99.front.like.dto.response;

import com.nhnacademy.store99.front.book.Response.BookPageResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class BookInfoForLikeResponse {

    private Long bookId;

    private String bookThumbnailUrl;

    private String bookTitle;

    private Integer bookPrice;

    private Integer bookSalePrice;

    private List<BookPageResponse.AuthorDTO> authorsDTOList;

    @Getter
    @Setter
    @Builder
    public static class AuthorDTO {
        private String authorName;
        private String authorType;

        public AuthorDTO(String authorName, String authorType) {
            this.authorName = authorName;
            this.authorType = authorType;
        }
    }

}
