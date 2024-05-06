package com.nhnacademy.store99.front.tag.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTagRequest {

    @NotBlank(message = "태그명 입력은 필수입니다.")
    @Size(max = 255)
    private String tagName;

}
