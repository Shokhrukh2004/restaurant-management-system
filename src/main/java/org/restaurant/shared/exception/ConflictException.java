package org.restaurant.shared.exception;

public class ConflictException extends RestaurantSystemException{
    public ConflictException(String message){
        super(message);
    }
}
