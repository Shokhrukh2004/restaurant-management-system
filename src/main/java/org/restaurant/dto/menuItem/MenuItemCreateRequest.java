package org.restaurant.dto.menuItem;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.restaurant.entity.enums.MenuCategory;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class MenuItemCreateRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 5, message = "Name should have minimum 5 characters")
    private final String name;

    @Length(max = 500, message = "Description max length 500 characters")
    private final String description;

    @PositiveOrZero(message = "Price should be positive or zero")
    private final BigDecimal price;

    @NotNull(message = "Menu item category cannot be null")
    private final MenuCategory menuCategory;
}
