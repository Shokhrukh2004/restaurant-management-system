package org.restaurant.parser;

import org.restaurant.dto.orderItem.OrderItemCreateRequest;
import org.restaurant.dto.orderItem.OrderItemResponse;
import org.restaurant.entity.OrderItem;

import java.util.List;

public class OrderItemParser {

    public static OrderItem toOrderItemFromCreateRequest(OrderItemCreateRequest request) {
        return OrderItem.builder()
                .quantity(request.getQuantity())
                .specialRequests(request.getSpecialRequests())
                .build();
    }

    public static List<OrderItemResponse> toOrderItemResponseList(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItemParser::toOrderItemResponseFromOrderItem)
                .toList();
    }

    public static OrderItemResponse toOrderItemResponseFromOrderItem(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .menuItemName(orderItem.getMenuItem().getName())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getItemPrice())
                .specialRequests(orderItem.getSpecialRequests())
                .status(orderItem.getOrderItemStatus())
                .build();
    }
}
