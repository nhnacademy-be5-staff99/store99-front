package com.nhnacademy.store99.front.order.dto;

import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class OrderBookInOrderDto {
    private String orderBookTitle;

    private Integer orderBookPrice;

    private Integer orderBookQuantity;

//    private Integer orderBookWrapperCost;

//    private Wrapper wrapper;

    private Long bookId;
}
