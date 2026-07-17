package org.restaurant.shared.validation;

import org.restaurant.shared.exception.ValidationException;

public class Validator {

    public static void validatePositiveInt(int value, String fieldName){
        if(value <= 0){
            throw new ValidationException(fieldName + " must be positive integer");
        }
    }

    public static void validateString(String value, String fieldName){
        if(value == null || value.isBlank()){
            throw new ValidationException(fieldName + " cannot be null or blank");
        }
    }
}
