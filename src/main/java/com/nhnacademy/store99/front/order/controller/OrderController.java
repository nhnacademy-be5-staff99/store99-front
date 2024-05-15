package com.nhnacademy.store99.front.order.controller;

import com.nhnacademy.store99.front.order.dto.request.OrderBookRequest;
import com.nhnacademy.store99.front.order.dto.request.OrderInquiryByGuestRequest;
import com.nhnacademy.store99.front.order.dto.response.BookInOrderResponse;
import com.nhnacademy.store99.front.order.dto.response.ConfirmPaymentResponse;
import com.nhnacademy.store99.front.order.dto.response.OrderInquiryResponse;
import com.nhnacademy.store99.front.order.service.OrderQueryService;
import com.nhnacademy.store99.front.order.service.OrderService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author seunggyu-kim
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderQueryService orderQueryService;
    private final OrderService orderService;

    @Value("${gateway.url}")
    private String gatewayUrl;

    @PostMapping("/checkout")
    public String getOrderIndex(@RequestAttribute boolean isLogin, Model model,
                                @ModelAttribute OrderBookRequest orderBookRequest) {
        List<BookInOrderResponse> orderBookList =
                orderQueryService.getOrderBookList(orderBookRequest.getOrderBookRequestList());

        int orderTotalCost = orderBookList.stream().mapToInt(o -> o.getBookSalePrice() * o.getQuantity()).sum();
        int orderDeliveryCost = 5000;

        model.addAttribute("orderId", UUID.randomUUID().toString());
        model.addAttribute("orderBookList", orderBookList);
        model.addAttribute("orderTotalCost", orderTotalCost);
        model.addAttribute("orderDeliveryCost", orderDeliveryCost);
        model.addAttribute("orderName", getOrderName(orderBookList));
        model.addAttribute("gatewayUrl", gatewayUrl);
        return isLogin ? "order/checkout_main_member" : "order/checkout_main_guest";
    }

    /**
     * 주문 도서 리스트로 받아서 주문명 반환
     * 주문 도서 외 n권 형식으로 반환
     *
     * @param orderBookList 주문 도서 리스트
     * @return 주문명
     */
    private @NotNull String getOrderName(final List<BookInOrderResponse> orderBookList) {
        if (orderBookList.size() == 1) {
            return orderBookList.get(0).getBookTitle();
        }
        return orderBookList.get(0).getBookTitle() + " 외 " + (orderBookList.size() - 1) + "권";
    }

    @GetMapping(value = "success")
    public String paymentResult(Model model, @RequestParam(value = "orderId") String orderId,
                                @RequestParam(value = "amount") Integer amount,
                                @RequestParam(value = "paymentKey") String paymentKey) throws Exception {
        log.info("결제 시도: orderId={}, amount={}, paymentKey={}", orderId, amount, paymentKey);
        ConfirmPaymentResponse confirmPaymentResponse = orderService.confirmPayment(orderId, amount, paymentKey);
        boolean isSuccess = confirmPaymentResponse.isSuccess();
        model.addAttribute("isSuccess", isSuccess);

        JSONObject jsonObject = confirmPaymentResponse.getJsonObject();
        model.addAttribute("responseStr", jsonObject.toJSONString());

        model.addAttribute("method", jsonObject.get("method"));
        model.addAttribute("orderName", jsonObject.get("orderName"));

        if (jsonObject.get("method") != null) {
            if (jsonObject.get("method").equals("카드")) {
                model.addAttribute("cardNumber", ((JSONObject) jsonObject.get("card")).get("number"));
            } else if (jsonObject.get("method").equals("가상계좌")) {
                model.addAttribute("accountNumber",
                        ((JSONObject) jsonObject.get("virtualAccount")).get("accountNumber"));
            } else if (jsonObject.get("method").equals("계좌이체")) {
                model.addAttribute("bank", ((JSONObject) jsonObject.get("transfer")).get("bank"));
            } else if (jsonObject.get("method").equals("휴대폰")) {
                model.addAttribute("customerMobilePhone",
                        ((JSONObject) jsonObject.get("mobilePhone")).get("customerMobilePhone"));
            }
        } else {
            model.addAttribute("code", jsonObject.get("code"));
            model.addAttribute("message", jsonObject.get("message"));
        }

        return "order/success";
    }

    @GetMapping(value = "fail")
    public String paymentResult(Model model, @RequestParam(value = "message") String message,
                                @RequestParam(value = "code") Integer code) throws Exception {
        log.error("결제 실패: code={}, message={}", code, message);

        model.addAttribute("code", code);
        model.addAttribute("message", message);

        return "order/fail";
    }

    @GetMapping("/order/guest")
    private String viewOrderInquiryFormByGuest(@RequestAttribute boolean isLogin) {
        if (isLogin) {
            return "redirect:/";
        }
        return "order/order_inquiry_guest";
    }

    @PostMapping("/order/guest")
    private String viewOrderByGuest(@RequestAttribute boolean isLogin,
                                    @ModelAttribute OrderInquiryByGuestRequest orderInquiryByGuestRequest,
                                    Model model) {
        if (isLogin) {
            return "redirect:/";
        }

        OrderInquiryResponse orderByGuest = orderQueryService.getOrderByGuest(orderInquiryByGuestRequest);
        model.addAttribute("orderForm", orderByGuest);
        return "order/order_page_guest";
    }
}
