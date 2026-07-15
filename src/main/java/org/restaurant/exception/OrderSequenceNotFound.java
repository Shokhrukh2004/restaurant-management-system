package org.restaurant.exception;

public class OrderSequenceNotFound extends RestaurantSystemException{
    public OrderSequenceNotFound(String message) {
        super(message);
    }
}
