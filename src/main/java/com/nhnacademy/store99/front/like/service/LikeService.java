package com.nhnacademy.store99.front.like.service;

import com.nhnacademy.store99.front.like.dto.request.LikeRequest;
import com.nhnacademy.store99.front.like.dto.response.BookInfoForLikeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LikeService {

    void addLike(LikeRequest likeRequest);

    String deleteLike(Long likeId);

    Long getLikeCnt(Long bookId);

    Page<BookInfoForLikeResponse> getLikeListByUser(Pageable pageable, Long userId);

}
