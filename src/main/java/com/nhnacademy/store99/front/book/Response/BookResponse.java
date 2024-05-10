package com.nhnacademy.store99.front.book.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookResponse {
    private Long BookId;
    private String BookIsbn13;
    private String BookIsbn10;
    private String BookTitle;
    private String BookContents;
    private String BookDescription;
    private String BookPublisher;
    private LocalDateTime BookDate;
    private Integer BookPrice;
    private Integer BookSalePrice;
    private Integer BookStock;
    private Integer BookCntOfReview;
    private Double BookAvgOfRate;
    private String BookImageURL;
    private String BookImageName;
    private List<BookResponse.AuthorDTO> authorsDTOList;
    private List<BookResponse.TagDTO> tagDTOList;

    @Getter
    @Setter
    @Builder
    public static class AuthorDTO {
        private String AuthorName;
        private String AuthorType;

        public AuthorDTO(String authorName, String authorType) {
            AuthorName = authorName;
            AuthorType = authorType;
        }
    }

    @Getter
    @Setter
    @Builder
    public static class TagDTO {
        @JsonProperty
        private String TagName;

        @JsonCreator
        public TagDTO(@JsonProperty("tagName") String tagName) {
            TagName = tagName;
        }
    }

}