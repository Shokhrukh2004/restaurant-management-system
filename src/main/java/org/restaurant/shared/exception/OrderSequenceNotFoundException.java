package org.restaurant.shared.exception;

public class OrderSequenceNotFoundException extends RestaurantSystemException{
    public OrderSequenceNotFoundException(String message) {
        super(message);
    }
}
