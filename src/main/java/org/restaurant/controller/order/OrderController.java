package org.restaurant.controller.order;

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
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(
            @Valid
            @RequestBody OrderCreateRequest orderCreateRequest){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderCreateRequest));
    }

    @PatchMapping("/{id}/{orderStatus}")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable int id,
            @PathVariable OrderStatus orderStatus){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.updateOrderStatus(id, orderStatus));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable int id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(
            @PathVariable OrderStatus status){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrdersByStatus(status));
    }
}
