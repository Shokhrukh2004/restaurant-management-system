package org.restaurant.service.orderItem;

import org.restaurant.dto.orderItem.OrderItemResponse;
import org.restaurant.entity.OrderItem;
import org.restaurant.exception.NotFoundException;
import org.restaurant.parser.OrderItemParser;
import org.restaurant.repository.OrderItemRepository;
import org.restaurant.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepo;
    private final OrderItemBusinessLogic orderItemLogic;

    public OrderItemService(OrderItemRepository orderItemRepo,
                            OrderItemBusinessLogic orderItemLogic) {
        this.orderItemRepo = orderItemRepo;
        this.orderItemLogic = orderItemLogic;
    }

    public OrderItemResponse updateQuantity(int itemId, int quantity){
        Validator.validatePositiveInt(itemId, "itemId");
        Validator.validatePositiveInt(quantity, "Quantity");

        OrderItem item = getOrderItemIfExists(itemId);
        orderItemLogic.orderNotDeliveredCheck(item
                        .getOrder()
                        .getOrderStatus());

        item.setQuantity(quantity);
        OrderItem updated = orderItemRepo.save(item);

        return OrderItemParser
                .toOrderItemResponseFromOrderItem(updated);

    }

    private OrderItem getOrderItemIfExists(int itemId){
        return orderItemRepo
                .findById(itemId)
                .orElseThrow(() -> new NotFoundException("Order item with id "
                        + itemId + " not found."));

    }
}
