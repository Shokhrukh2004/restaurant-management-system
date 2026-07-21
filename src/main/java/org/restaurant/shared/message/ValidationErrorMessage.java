package org.restaurant.shared.message;

/**
 * Validation error messages for all validation violations.
 * Code range: VAL_001 - VAL_099
 *
 * Includes: Type validation, business rules, constraints
 */
public enum ValidationErrorMessage {

    /** VAL_001: Positive integer validation failed */
    INVALID_POSITIVE_INT("VAL_001", "%s must be positive integer"),

    /** VAL_002: String cannot be null or blank */
    INVALID_BLANK_STRING("VAL_002", "%s cannot be null or blank"),

    /** VAL_003: String length validation failed */
    INVALID_LENGTH("VAL_003", "%s length must be between %d and %d characters"),

    // Business rules (high-level)
    /** VAL_004: Required field is missing */
    FIELD_REQUIRED("VAL_004", "%s is required"),

    /** VAL_005: Collection/List is empty */
    EMPTY_COLLECTION("VAL_005", "%s cannot be empty"),

    /** VAL_006: Constraint violation */
    CONSTRAINT_VIOLATION("VAL_006", "Constraint violation: %s"),

    /** VAL_007: Value exceeds maximum allowed */
    VALUE_TOO_LARGE("VAL_007", "%s cannot exceed %s"),

    /** VAL_008: Value is below minimum allowed */
    VALUE_TOO_SMALL("VAL_008", "%s must be at least %s"),

    /** VAL_009: Invalid email format */
    INVALID_EMAIL("VAL_009", "Invalid email format: %s"),

    /** VAL_010: Invalid format or pattern */
    INVALID_FORMAT("VAL_010", "Invalid format for %s"),

    /** VAL_011: Framework validation failed (Jakarta) */
    FRAMEWORK_VALIDATION_FAILED("VAL_011", "Validation failed: %s"),

    /** VAL_012: Invalid JSON format in request */
    INVALID_JSON_FORMAT("VAL_012", "Invalid request format"),

    /** VAL_013: Missing required request parameter */
    MISSING_REQUEST_PARAMETER("VAL_013", "Missing required parameter: %s"),
    ;

    private final String code;
    private final String message;

    ValidationErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String formatted(Object... args) {
        String result = code + "-" + message;
        return String.format(result, args);
    }
}