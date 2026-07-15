package org.restaurant.repository;

import org.restaurant.entity.OrderSequence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSequenceRepository extends JpaRepository<OrderSequence, String> {
}
