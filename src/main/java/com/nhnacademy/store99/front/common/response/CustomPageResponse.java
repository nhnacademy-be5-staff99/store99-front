package com.nhnacademy.store99.front.common.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class CustomPageResponse<T> extends PageImpl<T> {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomPageResponse(@JsonProperty("content") List<T> content, @JsonProperty("number") int number,
                              @JsonProperty("size") int size, @JsonProperty("totalElements") Long totalElements,
                              @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
                              @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort,
                              @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, 1), 10);
    }

    public CustomPageResponse(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public CustomPageResponse(List<T> content) {
        super(content);
    }
}
