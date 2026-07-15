package org.restaurant.parser;

import org.restaurant.dto.order.OrderCreateRequest;
import org.restaurant.dto.order.OrderResponse;
import org.restaurant.entity.Order;


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
