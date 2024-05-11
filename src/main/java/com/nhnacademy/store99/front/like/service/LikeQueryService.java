package com.nhnacademy.store99.front.like.service;

import com.nhnacademy.store99.front.like.dto.response.BookInfoForLikeResponse;
import java.util.List;

public interface LikeQueryService {
    List<BookInfoForLikeResponse> getAllByUser();
}
