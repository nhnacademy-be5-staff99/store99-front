package com.nhnacademy.store99.front.tag.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagResponse {
    @NotBlank
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String tagName;
}
