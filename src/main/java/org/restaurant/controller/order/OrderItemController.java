package org.restaurant.controller.order;

import org.restaurant.feature.order.dto.OrderItemResponse;
import org.restaurant.feature.order.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    private final OrderItemService itemService;

    public OrderItemController(OrderItemService itemService) {
        this.itemService = itemService;
    }

    @PutMapping("/{id}/{quantity}")
    public ResponseEntity<OrderItemResponse> update(
            @PathVariable int id,
            @PathVariable int quantity) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemService.updateQuantity(id, quantity));
    }
}
