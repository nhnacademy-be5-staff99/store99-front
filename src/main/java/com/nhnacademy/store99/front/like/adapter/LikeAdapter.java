package com.nhnacademy.store99.front.like.adapter;

import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.like.dto.request.LikeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "like-adapter", url = "${gateway.url}/api/bookstore/v1/likes")
public interface LikeAdapter  {

    @PostMapping
    CommonResponse<Void> addLike(final LikeRequest req);

    @DeleteMapping("/{bookId}/{userId}")
    CommonResponse<Void> deleteLike(@PathVariable final Long bookId, @PathVariable final Long userId);

//    @GetMapping
//    CommonResponse<CustomPageImpl<CategoryForAdminResponse>> getCategories(Pageable pageable);
}
