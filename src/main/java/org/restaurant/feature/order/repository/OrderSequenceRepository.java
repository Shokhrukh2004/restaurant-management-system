package org.restaurant.feature.order.repository;

import org.restaurant.feature.order.entity.OrderSequence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSequenceRepository extends JpaRepository<OrderSequence, String> {
}
