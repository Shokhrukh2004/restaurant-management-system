package org.restaurant.feature.order.service;

import org.restaurant.feature.order.dto.OrderItemResponse;
import org.restaurant.feature.order.entity.OrderItem;
import org.restaurant.feature.order.parser.OrderItemParser;
import org.restaurant.feature.order.repository.OrderItemRepository;
import org.restaurant.feature.order.business.OrderItemBusinessLogic;
import org.restaurant.shared.util.order.EntityFinder;
import org.restaurant.shared.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepo;
    private final OrderItemBusinessLogic orderItemLogic;
    private final EntityFinder entityFinder;

    public OrderItemService(OrderItemRepository orderItemRepo,
                            OrderItemBusinessLogic orderItemLogic,
                            EntityFinder entityFinder) {
        this.orderItemRepo = orderItemRepo;
        this.orderItemLogic = orderItemLogic;
        this.entityFinder = entityFinder;
    }

    public OrderItemResponse updateQuantity(int itemId, int quantity){
        Validator.validatePositiveInt(itemId, "itemId");
        Validator.validatePositiveInt(quantity, "Quantity");

        OrderItem item = entityFinder.getOrderItemIfExists(itemId);
        orderItemLogic.orderNotDeliveredCheck(
                item.getOrder().getOrderStatus());

        item.setQuantity(quantity);
        OrderItem updated = orderItemRepo.save(item);

        return OrderItemParser
                .toOrderItemResponseFromOrderItem(updated);
    }

}
