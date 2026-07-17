package org.restaurant.feature.order.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

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
