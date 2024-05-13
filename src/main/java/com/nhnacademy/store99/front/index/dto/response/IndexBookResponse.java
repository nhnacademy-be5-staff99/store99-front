package com.nhnacademy.store99.front.index.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class IndexBookResponse {
    private Long BookId;
    private String BookTitle;
    private LocalDateTime BookDate;
    private String BookDescription;
    private String BookThumbnailUrl;
}
