package com.nhnacademy.store99.front.order.adapter;

import com.nhnacademy.store99.front.order.dto.request.PaymentKeyRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author seunggyu-kim
 */
@FeignClient(value = "order-adapter", url = "${gateway.url}/open/bookstore/v1/orders")
public interface OrderOpenAdapter {
    @PostMapping("/{orderId}/success")
    void successPendingPayment(@PathVariable String orderId, @RequestBody PaymentKeyRequest paymentKeyRequest);

    @PostMapping("/{orderId}/fail")
    void failPendingPayment(@PathVariable String orderId, @RequestBody PaymentKeyRequest paymentKeyRequest);

    @DeleteMapping("/{orderId}")
    void undoPendingPayment(@PathVariable String orderId);
}