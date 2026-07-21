package org.restaurant.shared.exception;

public class ValidationException extends RestaurantSystemException{
    public ValidationException(String message) {
        super(message);
    }
}
