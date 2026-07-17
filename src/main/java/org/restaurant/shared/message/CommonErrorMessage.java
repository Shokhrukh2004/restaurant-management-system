package org.restaurant.shared.message;

/**
 * Common error messages used across all features.
 * Code range: COM_001 - COM_099
 */
public enum CommonErrorMessage {

    /** COM_001: Entity not found in database by id */
    NOT_FOUND("COM_001", "%s not found with id: %s"),

    /** COM_002: Entity already exists (duplicate entry) */
    DUPLICATE_ENTRY("COM_002", "%s already exists: %s"),

    /** ORD_001: Invalid status transition (e.g., DELIVERED -> PENDING) */
    INVALID_STATUS_TRANSITION("COM_003", "Cannot transition from %s to %s"),

    /** COM_003: User not authorized for operation */
    UNAUTHORIZED("COM_004", "Unauthorized access"),
    ;

    private final String code;
    private final String message;

    CommonErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Format message with provided arguments.
     * Example: CommonErrorMessage.NOT_FOUND.formatted("Order", 5)
     * Result: "Order not found with id: 5"
     */
    public String formatted(Object... args){
        return String.format(message, args);
    }
}
