package com.nhnacademy.store99.front.order.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentKeyRequest {
    private String paymentKey;

    private String response;

    private String method;

    private Integer paymentCost;
}
