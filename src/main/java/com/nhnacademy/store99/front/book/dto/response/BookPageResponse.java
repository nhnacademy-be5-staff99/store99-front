package com.nhnacademy.store99.front.book.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookPageResponse {

    private Long BookId;

    private String BookTitle;

    private String BookPublisher;

    private LocalDateTime BookDate;

    private Integer BookPrice;

    private Integer BookSalePrice;

    private String BookThumbnailUrl;

    private Integer BookCntOfReview;

    private Integer BookViewCount;

    private Integer BookStock;

    private Double BookAvgOfRate;

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
