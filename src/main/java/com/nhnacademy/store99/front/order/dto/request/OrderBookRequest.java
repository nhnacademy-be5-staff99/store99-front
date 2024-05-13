package com.nhnacademy.store99.front.order.dto.request;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author seunggyu-kim
 */
@Getter
@Setter
public class OrderBookRequest {
    @NotNull(message = "bookId는 필수입니다.")
    private Long bookId;

    @NotNull(message = "수량은 필수입니다.")
    @Min(value = 1, message = "수량은 1~100 사이여야 합니다.")
    @Max(value = 100, message = "수량은 1~100 사이여야 합니다.")
    private Integer quantity;

    private List<OrderBookRequest> orderBookRequestList;
}
