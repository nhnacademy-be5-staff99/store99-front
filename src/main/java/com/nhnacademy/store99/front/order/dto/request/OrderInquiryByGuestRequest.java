package com.nhnacademy.store99.front.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
@AllArgsConstructor
public class OrderInquiryByGuestRequest {
    private String orderId;

    private String orderPassword;
}
