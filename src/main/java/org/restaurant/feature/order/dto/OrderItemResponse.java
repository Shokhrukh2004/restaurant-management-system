package org.restaurant.feature.order.dto;

import lombok.Builder;
import lombok.Getter;
import org.restaurant.feature.order.enums.OrderItemStatus;

import java.math.BigDecimal;

@Getter
@Builder
public class OrderItemResponse {
    private final Integer id;
    private final Integer menuItemId;
    private final String menuItemName;
    private final int quantity;
    private final BigDecimal price;
    private final String specialRequests;
    private final OrderItemStatus status;
}
