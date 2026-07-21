package org.restaurant.feature.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest {

    @Valid
    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItemCreateRequest> orderItems;
}
