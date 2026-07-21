package org.restaurant.feature.order.message;


/**
 * Order-specific error messages.
 * Code range: ORD_001 - ORD_099
 */
public enum OrderErrorMessage {

    /** ORD_002: Order already completed/delivered */
    ORDER_ALREADY_DELIVERED("ORD_001", "Order is already delivered"),

    /** ORD_003: Order created with no items */
    NO_ITEMS_IN_ORDER("ORD_002", "Order must contain at least one item"),
    ;

    private final String code;
    private final String message;

    OrderErrorMessage(String code, String message) {
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
     * Example: OrderErrorMessage.INVALID_STATUS_TRANSITION.formatted("PENDING", "DELIVERED")
     * Result: "Cannot transition from PENDING to READY"
     */
    public String formatted(Object... args) {
        String result = code + "-" + message;
        return String.format(result, args);
    }
}