package org.restaurant.feature.order.service;

import org.restaurant.feature.order.entity.OrderSequence;
import org.restaurant.shared.exception.OrderSequenceNotFoundException;
import org.restaurant.feature.order.repository.OrderSequenceRepository;
import org.restaurant.shared.message.SystemErrorMessage;
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
        OrderSequence sequence = getOrderSequenceIfExists("order_number");

        long nextValue = sequence.getNextValue();
        sequence.setNextValue(nextValue + 1);
        sequenceRepo.save(sequence);

        return String.format("ORD-%5d", nextValue);
    }

    private OrderSequence getOrderSequenceIfExists(String sequenceId){
        return sequenceRepo.findById(sequenceId)
                .orElseThrow(() -> new OrderSequenceNotFoundException(SystemErrorMessage
                        .SEQUENCE_NOT_FOUND
                        .getMessage()));
    }
}
