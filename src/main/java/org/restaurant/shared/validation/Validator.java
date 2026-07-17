package org.restaurant.shared.validation;

import org.restaurant.shared.exception.ValidationException;
import org.restaurant.shared.message.ValidationErrorMessage;

public class Validator {

    public static void validatePositiveInt(int value, String fieldName){
        if(value <= 0){
            throw new ValidationException(ValidationErrorMessage
                    .INVALID_POSITIVE_INT
                    .formatted(fieldName));
        }
    }

    public static void validateString(String value, String fieldName){
        if(value == null || value.isBlank()){
            throw new ValidationException(ValidationErrorMessage
                    .INVALID_BLANK_STRING
                    .formatted(fieldName));
        }
    }
}
