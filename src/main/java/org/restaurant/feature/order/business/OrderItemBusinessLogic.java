package org.restaurant.feature.order.business;

import org.restaurant.feature.order.entity.OrderItem;
import org.restaurant.feature.order.enums.OrderItemStatus;
import org.restaurant.feature.order.enums.OrderStatus;
import org.restaurant.feature.order.message.OrderErrorMessage;
import org.restaurant.shared.exception.ConflictException;
import org.restaurant.shared.message.CommonErrorMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemBusinessLogic {

    public OrderItemBusinessLogic(){

    }

    public void orderNotDeliveredCheck(OrderStatus orderStatus){
        if(orderStatus.equals(OrderStatus.DELIVERED)){
            throw new ConflictException(OrderErrorMessage
                    .ORDER_ALREADY_DELIVERED
                    .formatted());
        }
    }

    public boolean orderTransitionToReadyCheck(List<OrderItem> orderItems){
        for(OrderItem orderItem : orderItems){
            if(orderItem.getOrderItemStatus().equals(OrderItemStatus.PENDING)){
                return false;
            }
        }

        return true;
    }

    public void orderItemStatusUpdateCheck(OrderItemStatus current, OrderItemStatus updated){
        if(!current.canTransition(updated)){
            throw new ConflictException(CommonErrorMessage
                    .INVALID_STATUS_TRANSITION
                    .formatted(current, updated));
        }
    }
}
