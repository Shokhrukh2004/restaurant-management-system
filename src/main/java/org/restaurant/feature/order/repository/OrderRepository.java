package org.restaurant.feature.order.repository;

import org.restaurant.feature.order.entity.Order;
import org.restaurant.feature.order.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByOrderByCreatedAtDesc();

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findByOrderStatus(OrderStatus orderStatus);


}
