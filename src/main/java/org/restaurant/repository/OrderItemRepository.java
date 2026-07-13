package org.restaurant.repository;
import org.restaurant.entity.Order;

import org.restaurant.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemRepository, Integer> {

    List<OrderItemRepository> findByOrder(Order order);

    List<OrderItemRepository> findByOrderItemStatus(OrderStatus orderStatus);
}
