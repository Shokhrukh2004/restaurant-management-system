package org.restaurant.exception;

public class ConflictException extends RestaurantSystemException{
    public ConflictException(String message){
        super(message);
    }
}
