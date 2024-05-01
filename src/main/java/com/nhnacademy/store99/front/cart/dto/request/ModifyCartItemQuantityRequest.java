package com.nhnacademy.store99.front.cart.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class ModifyCartItemQuantityRequest {
    @Min(1)
    @Max(100)
    @NotNull
    private final Integer quantity = 1;
}
