package org.restaurant.shared.handler;

import org.restaurant.shared.dto.ErrorResponse;
import org.restaurant.shared.message.CommonErrorMessage;
import org.restaurant.shared.message.SystemErrorMessage;
import org.restaurant.shared.message.ValidationErrorMessage;
import org.restaurant.shared.util.handler.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class FrameworkExceptionHandler {
    /**
     * Handle MethodArgumentNotValidException (400)
     * Triggered by @Valid on request body (Jakarta Bean Validation)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        String errors = getValidationErrors(ex);

        String errorMessage = ValidationErrorMessage
                .FRAMEWORK_VALIDATION_FAILED
                .formatted(errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Util.buildResponse(errorMessage, request));

    }

    /**
     * Handle HttpMessageNotReadableException (400)
     * Triggered by invalid JSON format in request body
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex,
            WebRequest request) {

        String message = ValidationErrorMessage
                .INVALID_JSON_FORMAT
                .formatted(ex.getMessage());

        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Util.buildResponse(message, request));
    }

    /**
     * Handle MissingServletRequestParameterException (400)
     * Triggered by missing required request parameters
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex,
            WebRequest request) {

        String message = ValidationErrorMessage
                .MISSING_REQUEST_PARAMETER
                .formatted(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Util.buildResponse(message, request));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpMessageNotReadableException ex,
            WebRequest request) {

        String message = SystemErrorMessage
                .METHOD_NOT_SUPPORTED
                .formatted(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(Util.buildResponse(message, request));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            WebRequest request) {

        String message = CommonErrorMessage
                .NOT_FOUND
                .formatted("Endpoint", ex.getRequestURL());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Util.buildResponse(message, request));
    }

    private String getValidationErrors(MethodArgumentNotValidException ex){
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining());
    }
}
