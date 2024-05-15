package com.nhnacademy.store99.front.order.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class OrderInquiryResponse {
    private String orderState;
    private LocalDateTime orderReleaseAt;
    private Integer orderTotalCost;
    private Integer orderDeliveryCost;
    private Integer orderUsePoint;
    private Integer couponDiscount;
    private Integer orderFinalCost;

    private List<BookInOrderInquiryResponse> orderBookList;

    private String buyerName;
    private String buyerPhone;
    private String buyerEmail;

    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private String recipientPostcode;
    private String recipientDetailAddress;
}
