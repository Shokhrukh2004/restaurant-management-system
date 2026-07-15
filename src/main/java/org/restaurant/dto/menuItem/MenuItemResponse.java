package org.restaurant.dto.menuItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.restaurant.entity.enums.MenuCategory;

import java.math.BigDecimal;

@Getter
@Builder
public class MenuItemResponse {
    private final Integer id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final MenuCategory category;
    private final boolean isAvailable;
}
