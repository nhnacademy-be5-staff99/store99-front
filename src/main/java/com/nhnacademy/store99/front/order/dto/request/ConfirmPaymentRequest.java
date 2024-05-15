package com.nhnacademy.store99.front.order.dto.request;

import com.nhnacademy.store99.front.order.dto.ConsumerInOrderDto;
import com.nhnacademy.store99.front.order.dto.OrderBookInOrderDto;
import com.nhnacademy.store99.front.order.dto.OrderDto;
import java.util.List;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class ConfirmPaymentRequest {
    private ConsumerInOrderDto consumer;

    private OrderDto order;

    private List<OrderBookInOrderDto> orderBooks;
}
