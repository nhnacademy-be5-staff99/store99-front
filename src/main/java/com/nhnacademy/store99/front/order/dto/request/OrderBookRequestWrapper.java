package com.nhnacademy.store99.front.order.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBookRequestWrapper {
    private List<OrderBookRequest> orderBookRequestList;
}
