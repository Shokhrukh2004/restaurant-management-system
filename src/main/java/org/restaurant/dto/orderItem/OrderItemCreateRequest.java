package org.restaurant.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.restaurant.entity.MenuItem;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderItemCreateRequest {

    @Positive(message = "Menu Item Id should be positive integer")
    private final int menuItemId;

    @Positive(message = "Quantity should be minimum 1")
    private final int quantity;

    @Length(max = 500, message = "Maximum length is 500 characters")
    private final String specialRequests;
}
