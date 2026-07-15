package org.restaurant.repository;
import org.restaurant.entity.Order;

import org.restaurant.entity.OrderItem;
import org.restaurant.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByOrderItemStatus(OrderStatus orderStatus);
}
