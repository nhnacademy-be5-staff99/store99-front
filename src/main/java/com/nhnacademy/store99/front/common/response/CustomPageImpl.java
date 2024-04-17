package com.nhnacademy.store99.front.common.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Page를 반환받을 때 뜨는 Jackson parsing 오류를 해결하기 위한 커스텀 객체
 *
 * @param <T> 페이지 안에 들어가는 객체 타입
 * @author seunggyu-kim
 * @author yrrho2
 */
public class CustomPageImpl<T> extends PageImpl<T> {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomPageImpl(@JsonProperty("content") List<T> content, @JsonProperty("number") int number,
                          @JsonProperty("size") int size, @JsonProperty("totalElements") Long totalElements,
                          @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
                          @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort,
                          @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public CustomPageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public CustomPageImpl(List<T> content) {
        super(content);
    }

    public CustomPageImpl() {
        super(new ArrayList<>());
    }
}