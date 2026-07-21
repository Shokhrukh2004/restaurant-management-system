package org.restaurant.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.restaurant.feature.order.dto.OrderCreateRequest;
import org.restaurant.feature.order.dto.OrderResponse;
import org.restaurant.feature.order.enums.OrderStatus;
import org.restaurant.feature.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Tag(name = "Orders", description = "Order management endpoints")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(summary = "Create new order", description = "Create a new order with items")
    @ApiResponse(responseCode = "201", description = "Order created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid order data")
    @ApiResponse(responseCode = "404", description = "Menu item not found")
    public ResponseEntity<OrderResponse> save(
            @Valid
            @RequestBody OrderCreateRequest orderCreateRequest){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderCreateRequest));
    }

    @PatchMapping("/{id}/{orderStatus}")
    @Operation(summary = "Update order status", description = "Change order status (PENDING→READY→DELIVERED)")
    @ApiResponse(responseCode = "200", description = "Status updated")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "409", description = "Invalid status transition")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable int id,
            @PathVariable OrderStatus orderStatus){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.updateOrderStatus(id, orderStatus));
    }

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieve all orders")
    @ApiResponse(responseCode = "200", description = "List of orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieve a specific order")
    @ApiResponse(responseCode = "200", description = "Order found")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable int id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderById(id));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get orders by status", description = "Get all orders with specific status")
    @ApiResponse(responseCode = "200", description = "List of orders with status")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(
            @PathVariable OrderStatus status){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrdersByStatus(status));
    }
}
