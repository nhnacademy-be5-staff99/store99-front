package com.nhnacademy.store99.front.order.service;

import com.nhnacademy.store99.front.order.dto.request.OrderBookRequest;
import com.nhnacademy.store99.front.order.dto.response.BookInOrderResponse;
import java.util.List;

/**
 * @author seunggyu-kim
 */
public interface OrderQueryService {
    List<BookInOrderResponse> getOrderBookList(final List<OrderBookRequest> orderBookRequestList);
}
