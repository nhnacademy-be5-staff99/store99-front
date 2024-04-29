package com.nhnacademy.store99.front.cart.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
@AllArgsConstructor
public class CartItemRequest {
    @NotNull(message = "bookId는 필수입니다.")
    private Long bookId;

    @Min(value = 1, message = "수량은 1~100 사이여야 합니다.")
    @Max(value = 100, message = "수량은 1~100 사이여야 합니다.")
    @NotNull(message = "수량은 필수입니다.")
    private Integer quantity = 1;
}
