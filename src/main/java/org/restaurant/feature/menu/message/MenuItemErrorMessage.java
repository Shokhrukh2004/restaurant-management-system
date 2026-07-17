package org.restaurant.feature.menu.message;

/**
 * Menu Item-specific error messages.
 * Code range: MENU_001 - MENU_099
 *
 * Note: Use CommonErrorMessage.NOT_FOUND for "not found" errors
 * Note: Use CommonErrorMessage.DUPLICATE_ENTRY for name duplicates
 * Note: Use ValidationErrorMessage for validation errors
 */
public enum MenuItemErrorMessage {

    /** MENU_001: Menu item is not available for ordering */
    MENU_ITEM_UNAVAILABLE("MENU_001", "Menu Item is not available"),

    /** MENU_002: Menu item already set to available */
    MENU_ITEM_ALREADY_AVAILABLE("MENU_002", "Menu Item is already set to available"),

    /** MENU_003: Menu item already set to unavailable */
    MENU_ITEM_ALREADY_UNAVAILABLE("MENU_003", "Menu Item is already set to unavailable"),
    ;

    private final String code;
    private final String message;

    MenuItemErrorMessage(String code, String message) {
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
     * Example: MenuItemErrorMessage.MENU_ITEM_UNAVAILABLE.formatted()
     * Result: "Menu Item is not available"
     */
    public String formatted(Object... args) {
        return String.format(message, args);
    }
}