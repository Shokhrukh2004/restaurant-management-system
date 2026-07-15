package org.restaurant.service.orderItem;

import org.restaurant.entity.OrderItem;
import org.restaurant.entity.enums.OrderStatus;
import org.restaurant.exception.ConflictException;
import org.springframework.stereotype.Component;

@Component
public class OrderItemBusinessLogic {

    public OrderItemBusinessLogic(){

    }

    public void orderNotDeliveredCheck(OrderStatus orderStatus){
        if(orderStatus.equals(OrderStatus.DELIVERED)){
            throw new ConflictException("Invalid Request: cannot change order after being delivered.");
        }
    }
}
