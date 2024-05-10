package com.nhnacademy.store99.front.tag.adapter;

import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.tag.dto.request.CreateTagRequest;
import com.nhnacademy.store99.front.tag.dto.request.ModifyTagRequest;
import com.nhnacademy.store99.front.tag.dto.response.TagResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * OpenFeign으로 백엔드 서버와 통신
 *
 * @author rosin23
 */

@FeignClient(value = "tagAdapter", url = "${gateway.url}/api/bookstore/admin/v1/tags")
public interface TagAdapter {
    @PostMapping
    CommonResponse<Void> createTag(@RequestBody CreateTagRequest request);

    @GetMapping
    CommonResponse<List<TagResponse>> getAllTags();

    @GetMapping("/{id}")
    CommonResponse<Void> getTagById(@PathVariable Long id);

    @PutMapping("/{id}")
    CommonResponse<Void> updateTag(@PathVariable Long id, ModifyTagRequest request);

    @DeleteMapping("/{id}")
    CommonResponse<Void> deleteTag(@PathVariable Long id);


}
