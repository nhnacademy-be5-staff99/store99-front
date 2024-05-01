package com.nhnacademy.store99.front.search.service;

import com.nhnacademy.store99.front.search.adapter.BasicSearchAdapter;
import com.nhnacademy.store99.front.search.dto.BasicSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ahyeon Song
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicSearchService {

    private final BasicSearchAdapter searchAdapter;

    /**
     * 도서 제목 또는 저자 이름에 content 가 포함된 도서 목록 반환
     */
    public Page<BasicSearchResponse> basicSearchResult(String content, Pageable pageable) {
        return searchAdapter.searchBooksByContent(content, pageable).getResult();
    }
}
