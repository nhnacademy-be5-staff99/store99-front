package com.nhnacademy.store99.front.like.service;

import com.nhnacademy.store99.front.like.dto.request.LikeRequest;

public interface LikeService {

    void addLike(LikeRequest likeRequest);

    String deleteLike(Long likeId);
}
