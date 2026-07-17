package org.restaurant.feature.order.service;

import org.restaurant.feature.order.entity.OrderSequence;
import org.restaurant.shared.exception.OrderSequenceNotFound;
import org.restaurant.feature.order.repository.OrderSequenceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderNumberGenerator {
    private final OrderSequenceRepository sequenceRepo;

    public OrderNumberGenerator(OrderSequenceRepository sequenceRepo) {
        this.sequenceRepo = sequenceRepo;
    }

    @Transactional
    public String generateOrderNumber(){
        OrderSequence sequence = sequenceRepo.findById("order_number")
                .orElseThrow(() -> new OrderSequenceNotFound("Order sequence number not found"));

        long nextValue = sequence.getNextValue();
        sequence.setNextValue(nextValue + 1);
        sequenceRepo.save(sequence);

        return String.format("ORD-%5d", nextValue);
    }
}
