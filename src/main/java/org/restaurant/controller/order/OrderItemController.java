package org.restaurant.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.restaurant.feature.order.dto.OrderItemResponse;
import org.restaurant.feature.order.enums.OrderItemStatus;
import org.restaurant.feature.order.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderItem")
@Tag(name = "Order Items", description = "Order item management endpoints")
public class OrderItemController {
    private final OrderItemService itemService;

    public OrderItemController(OrderItemService itemService) {
        this.itemService = itemService;
    }

    @PutMapping("/quantity/{id}/{quantity}")
    @Operation(summary = "Update item quantity", description = "Update quantity of an order item")
    @ApiResponse(responseCode = "200", description = "Quantity updated")
    @ApiResponse(responseCode = "404", description = "Order item not found")
    @ApiResponse(responseCode = "409", description = "Cannot modify delivered order")
    public ResponseEntity<OrderItemResponse> updateQuantity(
            @PathVariable int id,
            @PathVariable int quantity) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemService.updateQuantity(id, quantity));
    }

    @PutMapping("/status/{id}/{status}")
    @Operation(summary = "Update item status", description = "Update status of an order item")
    @ApiResponse(responseCode = "200", description = "Status updated")
    @ApiResponse(responseCode = "404", description = "Order item not found")
    @ApiResponse(responseCode = "409", description = "Invalid status transition")
    public ResponseEntity<OrderItemResponse> updateStatus(
            @PathVariable int id,
            @PathVariable OrderItemStatus status){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemService.updateOrderItemStatus(id, status));
    }
}


