package com.nhnacademy.store99.front.order.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class OrderDto {
    @Size(max = 36)
    @NotNull
    private String orderId;     // UUID

    @Size(max = 255)
    @NotNull
    private String orderName;

    @NotNull
    private String orderType;

    @NotNull
    private LocalDate orderTransitAt;

    @Size(max = 50)
    @NotNull
    private String orderReceiverName;

    @Size(max = 11)
    @NotNull
    private String orderReceiverNumber;

    @Size(max = 255)
    @NotNull
    private String orderAddress;

    @Size(max = 255)
    @NotNull
    private String orderAddressDetail;

    @NotNull
    private Integer orderAddressCode;

    @NotNull
    private Integer orderDeliveryCost;

    @NotNull
    private Integer orderTotalCost;

    private Integer orderUsePoint;

    @NotNull
    private Integer orderFinalCost;

    private Long couponId;

    private Integer couponDiscount;
}
