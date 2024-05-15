package com.nhnacademy.store99.front.order.controller;

import com.nhnacademy.store99.front.order.dto.request.ConfirmPaymentRequest;
import com.nhnacademy.store99.front.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public void confirmPayment(@RequestBody ConfirmPaymentRequest confirmPaymentRequest) {
        orderService.confirmPayment(confirmPaymentRequest);
    }

    @DeleteMapping("/orders/{orderId}")
    public void undoPendingPayment(@PathVariable String orderId) {
        orderService.undoPayment(orderId);
    }

}
