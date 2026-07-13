package org.restaurant.dto.menuItem;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.restaurant.entity.enums.MenuCategory;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class MenuItemRequest {

    @NotBlank(message = "Name is required")
    @Min(value = 5, message = "Name should have minimum 5 characters")
    private final String name;

    private final String description;

    @PositiveOrZero(message = "Price should be positive or zero")
    private final BigDecimal price;

    @NotNull(message = "Menu item category cannot be null")
    private final MenuCategory menuCategory;
}
