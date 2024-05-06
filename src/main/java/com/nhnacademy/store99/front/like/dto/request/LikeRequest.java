package com.nhnacademy.store99.front.like.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeRequest {

    Long bookId;

    Long userId;
}
