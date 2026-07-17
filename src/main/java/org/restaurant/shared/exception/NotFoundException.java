package org.restaurant.shared.exception;

public class NotFoundException extends RestaurantSystemException{

    public NotFoundException(String message) {
        super(message);
    }
}
