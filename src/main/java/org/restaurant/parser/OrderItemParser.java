package org.restaurant.parser;

import org.restaurant.dto.orderItem.OrderItemCreateRequest;
import org.restaurant.entity.OrderItem;

public class OrderItemParser {

    public static OrderItem toOrderItemFromCreateRequest(OrderItemCreateRequest request) {
        return OrderItem.builder()
                .menuItem(request.getMenuItem())
                .quantity(request.getQuantity())
                .itemPrice(request.getItemPrice())
                .specialRequests(request.getSpecialRequests())
                .build();
    }
}
