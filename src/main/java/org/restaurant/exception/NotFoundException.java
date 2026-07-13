package org.restaurant.exception;

public class NotFoundException extends RestaurantSystemException{

    public NotFoundException(String message) {
        super(message);
    }
}
