package org.restaurant.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.restaurant.dto.orderItem.OrderItemCreateRequest;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderCreateRequest {

    @Valid
    @NotEmpty(message = "Order must contain at least one item")
    private final List<OrderItemCreateRequest> orderItems;
}
