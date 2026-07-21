package org.restaurant.feature.order.enums;

import java.util.EnumSet;
import java.util.Set;

public enum OrderStatus {
    PENDING,
    READY,
    DELIVERED;

    private Set<OrderStatus> allowedMoves;

    static{
        PENDING.allowedMoves = EnumSet.of(READY);
        READY.allowedMoves = EnumSet.of(DELIVERED);
        DELIVERED.allowedMoves = EnumSet.noneOf(OrderStatus.class);
    }

    public boolean canTransition(OrderStatus orderStatus){
        return allowedMoves.contains(orderStatus);
    }
}
