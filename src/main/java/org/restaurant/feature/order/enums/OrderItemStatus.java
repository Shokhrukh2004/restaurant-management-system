package org.restaurant.feature.order.enums;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.EnumSet;
import java.util.Set;

public enum OrderItemStatus {
    PENDING,
    READY;

    private Set<OrderItemStatus> allowedMoves;

    static{
        PENDING.allowedMoves = EnumSet.of(READY);
        READY.allowedMoves = EnumSet.noneOf(OrderItemStatus.class);
    }

    public boolean canTransition(OrderItemStatus status){
        return allowedMoves.contains(status);
    }
}
