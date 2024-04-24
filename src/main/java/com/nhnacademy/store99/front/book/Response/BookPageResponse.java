package com.nhnacademy.store99.front.book.Response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>도서</h1>
 * 도서 번호
 * isbn 13,10
 * 제목
 * 목차
 * 설명
 * 출판사
 * 출판일시
 * 정가
 * 판매가
 * 포장가능여부
 * 썸네일
 * 조회수
 * 재고
 * 리뷰 수
 * 평균평점
 * 생성일시
 * 수정일시
 * <h1>Other</h1>
 * 작가 이름
 * 작가 역할
 * 삭제일시 != null
 */
public class BookPageResponse {

    private Long BookId;

    private String BookIsbn13;

    private String BookIsbn10;

    private String BookTitle;

    private String BookContents;

    private String BookPublisher;

    private LocalDateTime BookDate;

    private Integer BookPrice;

    private Integer BookSalePrice;

    private Boolean BookIsPacked;

    private String BookThumbnailUrl;

    private Integer BookStock;

    private Integer BookCntOfReview;

    private Double BookAvgOfRate;

    private LocalDateTime CreatedAt;

    private LocalDateTime UpdatedAt;

    private List<AuthorDTO> authorsDTOList;

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

}
