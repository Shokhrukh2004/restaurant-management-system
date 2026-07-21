package org.restaurant.feature.order.parser;

import org.restaurant.feature.order.dto.OrderCreateRequest;
import org.restaurant.feature.order.dto.OrderResponse;
import org.restaurant.feature.order.entity.Order;


public class OrderParser {

    public static Order toOrderFromCreateRequest(OrderCreateRequest request) {
        return Order.builder()
                .build();
    }

    public static OrderResponse toOrderResponseFromOrder(Order order){

        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderStatus(order.getOrderStatus())
                .orderItems(OrderItemParser
                        .toOrderItemResponseList(order.getOrderItems()))
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
