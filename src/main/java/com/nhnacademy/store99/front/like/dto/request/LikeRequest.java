package com.nhnacademy.store99.front.like.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeRequest {

    LocalDateTime createdAt;

    Long bookId;

    Long userId;
}
