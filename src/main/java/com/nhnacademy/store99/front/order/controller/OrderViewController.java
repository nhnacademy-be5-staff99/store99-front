package com.nhnacademy.store99.front.order.controller;

import com.nhnacademy.store99.front.order.dto.request.OrderBookRequest;
import com.nhnacademy.store99.front.order.dto.response.BookInOrderResponse;
import com.nhnacademy.store99.front.order.service.OrderQueryService;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
@Controller
@RequiredArgsConstructor
public class OrderViewController {
    private final OrderQueryService orderQueryService;

    @PostMapping("/checkout")
    public String getOrderIndex(@RequestAttribute boolean isLogin, Model model,
                                @ModelAttribute OrderBookRequest orderBookRequest) {
        List<BookInOrderResponse> orderBookList =
                orderQueryService.getOrderBookList(orderBookRequest.getOrderBookRequestList());
        model.addAttribute("orderId", UUID.randomUUID().toString());
        model.addAttribute("orderBookList", orderBookList);
        model.addAttribute("totalProductPrice",
                orderBookList.stream().mapToInt(BookInOrderResponse::getBookSalePrice).sum());
        model.addAttribute("orderName", getOrderName(orderBookList));
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

        String secretKey = "test_sk_Z1aOwX7K8m12DZ9omOOq8yQxzvNP:";

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(secretKey.getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        URL url = new URL("https://api.tosspayments.com/v1/payments/" + paymentKey);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes(StandardCharsets.UTF_8));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;
        model.addAttribute("isSuccess", isSuccess);

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();
        model.addAttribute("responseStr", jsonObject.toJSONString());
        System.out.println(jsonObject.toJSONString());

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

        model.addAttribute("code", code);
        model.addAttribute("message", message);

        return "order/fail";
    }
}
