package com.nhnacademy.store99.front.order.service;

import com.nhnacademy.store99.front.order.dto.request.ConfirmPaymentRequest;
import com.nhnacademy.store99.front.order.dto.response.ConfirmPaymentResponse;

/**
 * @author seunggyu-kim
 */
public interface OrderService {
    ConfirmPaymentResponse confirmPayment(String orderId, Integer amount, String paymentKey) throws Exception;

    void undoPayment(String orderId);

    void confirmPayment(ConfirmPaymentRequest confirmPaymentRequest);
}
