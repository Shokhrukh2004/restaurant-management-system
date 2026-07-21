package org.restaurant.shared.handler;

import org.restaurant.shared.dto.ErrorResponse;
import org.restaurant.shared.exception.ConflictException;
import org.restaurant.shared.exception.NotFoundException;
import org.restaurant.shared.exception.ValidationException;
import org.restaurant.shared.util.handler.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Exception handler for domain-specific errors.
 * Handles: NotFoundException, ConflictException, ValidationException
 */
@RestControllerAdvice
public class DomainExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            NotFoundException ex,
            WebRequest request) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Util.buildResponse(ex.getMessage(), request));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(
            ConflictException ex,
            WebRequest request) {

        return  ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Util.buildResponse(ex.getMessage(), request));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            ValidationException ex,
            WebRequest request){

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Util.buildResponse(ex.getMessage(), request));
    }

}
