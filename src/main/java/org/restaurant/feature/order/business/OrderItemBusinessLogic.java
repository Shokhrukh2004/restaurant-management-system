package org.restaurant.feature.order.business;

import org.restaurant.feature.order.enums.OrderStatus;
import org.restaurant.shared.exception.ConflictException;
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
