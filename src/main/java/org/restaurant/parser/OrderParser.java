package org.restaurant.parser;

import org.restaurant.dto.order.OrderCreateRequest;
import org.restaurant.dto.orderItem.OrderItemCreateRequest;
import org.restaurant.entity.Order;
import org.restaurant.entity.OrderItem;

import java.util.List;

import static org.restaurant.parser.OrderItemParser.toOrderItemFromCreateRequest;

public class OrderParser {

    public static Order toOrderFromCreateRequest(OrderCreateRequest request) {

        Order order = Order.builder()
                .build();

        addOrderReferenceToOrderItems(order, request.getOrderItems());

        return order;
    }

    private static void addOrderReferenceToOrderItems(Order order, List<OrderItemCreateRequest> requests){
        for(OrderItemCreateRequest requestItem : requests){
            OrderItem item = toOrderItemFromCreateRequest(requestItem);
            order.addItem(item);
        }
    }
}
