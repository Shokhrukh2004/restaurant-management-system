package org.restaurant.feature.order.repository;
import org.restaurant.feature.order.entity.Order;

import org.restaurant.feature.order.entity.OrderItem;
import org.restaurant.feature.order.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByOrderItemStatus(OrderStatus orderStatus);
}
