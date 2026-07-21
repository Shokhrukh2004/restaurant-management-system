package org.restaurant.feature.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.restaurant.feature.order.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponse {
    private final Integer id;
    private final String orderNumber;
    private final OrderStatus orderStatus;
    private final List<OrderItemResponse> orderItems;
    private final BigDecimal totalPrice;
}
