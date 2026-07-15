package org.restaurant.service.order;

import jakarta.persistence.Column;
import org.restaurant.entity.OrderSequence;
import org.restaurant.exception.OrderSequenceNotFound;
import org.restaurant.repository.OrderSequenceRepository;
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
