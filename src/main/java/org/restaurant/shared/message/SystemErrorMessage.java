package org.restaurant.shared.message;

/**
 * System-level error messages for infrastructure issues.
 * Code range: SYS_001 - SYS_099
 */
public enum SystemErrorMessage {
    /** SYS_004: Database operation failed */
    DATABASE_ERROR("SYS_001", "Database error occurred"),

    /** SYS_005: Transaction failed or rolled back */
    TRANSACTION_ERROR("SYS_002", "Transaction failed: %s"),

    /** SYS_006: Sequence not found in database */
    SEQUENCE_NOT_FOUND("SYS_003", "Sequence not found in database"),

    /** SYS_007: General server error */
    INTERNAL_SERVER_ERROR("SYS_004", "Internal server error"),

    /** SYS_008: Bad request format or structure */
    BAD_REQUEST("SYS_005", "Bad request: %s"),

    METHOD_NOT_SUPPORTED("SYS_006", "Method not supported"),
    ;

    private final String code;
    private final String message;

    SystemErrorMessage(String code, String message) {
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
     * Example: SystemErrorMessage.TRANSACTION_ERROR.formatted("Update order")
     * Result: "Order ID must be positive integer"
     */
    public String formatted(Object... args){
        String result = code + "-" + message;
        return String.format(result, args);
    }
}
