package org.restaurant.shared.handler;

import org.restaurant.shared.dto.ErrorResponse;
import org.restaurant.shared.exception.RestaurantSystemException;
import org.restaurant.shared.util.handler.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Exception handler for system-level errors.
 * Handles: RestaurantSystemException (database, transaction, sequence errors)
 */
@RestControllerAdvice
public class SystemExceptionHandler {

    @ExceptionHandler(RestaurantSystemException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantSystemException(
            RestaurantSystemException ex,
            WebRequest request) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Util.buildResponse(ex.getMessage(), request));
    }
}
