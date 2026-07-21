package org.restaurant.feature.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.restaurant.feature.menu.enums.MenuCategory;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class MenuItemResponse {
    private final Integer id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final MenuCategory category;
    private final boolean isAvailable;
}
