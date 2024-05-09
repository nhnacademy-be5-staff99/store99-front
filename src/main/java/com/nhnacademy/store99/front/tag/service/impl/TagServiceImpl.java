package com.nhnacademy.store99.front.tag.service.impl;

import com.nhnacademy.store99.front.tag.adapter.TagAdapter;
import com.nhnacademy.store99.front.tag.dto.request.CreateTagRequest;
import com.nhnacademy.store99.front.tag.dto.request.ModifyTagRequest;
import com.nhnacademy.store99.front.tag.dto.response.TagResponse;
import com.nhnacademy.store99.front.tag.service.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * tag 서비스 구현
 *
 * @author rosin23
 */

@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {

    private final TagAdapter tagAdapter;

    @Override
    public List<TagResponse> findAllTags() {
        return tagAdapter.getAllTags().getResult();
    }

    @Override
    public void createTag(CreateTagRequest request) {
        tagAdapter.createTag(request);
    }

    @Override
    public void updateTag(Long id, ModifyTagRequest request) {
        tagAdapter.updateTag(id, request);
    }

    @Override
    public void deleteTag(Long id) {
        tagAdapter.deleteTag(id);
    }
}
