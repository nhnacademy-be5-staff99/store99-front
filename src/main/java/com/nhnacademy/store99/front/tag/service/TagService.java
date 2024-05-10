package com.nhnacademy.store99.front.tag.service;

import com.nhnacademy.store99.front.tag.dto.request.CreateTagRequest;
import com.nhnacademy.store99.front.tag.dto.request.ModifyTagRequest;
import com.nhnacademy.store99.front.tag.dto.response.TagResponse;
import java.util.List;

/**
 * 프론트 태그 서비스
 *
 * @author rosin23
 */

public interface TagService {
    List<TagResponse> findAllTags();

    void createTag(CreateTagRequest request);

    void updateTag(Long id, ModifyTagRequest request);

    void deleteTag(Long id);
}
