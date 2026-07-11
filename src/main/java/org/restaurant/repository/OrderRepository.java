package org.restaurant.repository;

import org.restaurant.entity.Order;
import org.restaurant.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByOrderByCreatedAtDesc();

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findByOrderStatus(OrderStatus orderStatus);


}
