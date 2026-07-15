package org.restaurant.dto.order;

import lombok.Builder;
import lombok.Getter;
import org.restaurant.dto.orderItem.OrderItemResponse;
import org.restaurant.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class OrderResponse {
    private final Integer id;
    private final String orderNumber;
    private final OrderStatus orderStatus;
    private final List<OrderItemResponse> orderItems;
    private final BigDecimal totalPrice;
}
